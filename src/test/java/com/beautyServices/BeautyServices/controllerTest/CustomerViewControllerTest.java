package com.beautyServices.BeautyServices.controllerTest;

import com.beautyServices.BeautyServices.controller.CustomerViewController;
import com.beautyServices.BeautyServices.entity.Customer;
import com.beautyServices.BeautyServices.repository.CustomerRepository;
import com.beautyServices.BeautyServices.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerViewController.class)
@ExtendWith(MockitoExtension.class)
public class CustomerViewControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldRequireAuthenticationForCustomerList() throws Exception {
        mockMvc.perform(get("/customers")
                        .with(SecurityMockMvcRequestPostProcessors.anonymous()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnCustomerListView() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        mockMvc.perform(get("/customers")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/customer-list"))
                .andExpect(model().attribute("customers", customers));
    }

    @Test
    public void shouldReturnCustomerAddView() throws Exception {
        mockMvc.perform(get("/customers/add")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/customer-add"))
                .andExpect(model().attributeExists("customers"));
    }

    @Test
    public void shouldAddCustomer() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setName("Test Name");
        newCustomer.setMobileNumber("123456789");
        newCustomer.setEmail("test@example.com");
        newCustomer.setUsername("testuser");
        newCustomer.setPassword("testpassword");

        Mockito.when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        mockMvc.perform(post("/customers/add")
                        .param("name", newCustomer.getName())
                        .param("mobileNumber", newCustomer.getMobileNumber())
                        .param("email", newCustomer.getEmail())
                        .param("username", newCustomer.getUsername())
                        .param("password", newCustomer.getPassword())
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerRepository).save(customerCaptor.capture());
        Customer savedCustomer = customerCaptor.getValue();

        assertEquals("Test Name", savedCustomer.getName());
        assertEquals("123456789", savedCustomer.getMobileNumber());
        assertEquals("test@example.com", savedCustomer.getEmail());
        assertEquals("testuser", savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword());
    }

    @Test
    public void shouldReturnCustomerEditView() throws Exception {
        Long customerId = 1L;
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setName("Existing Name");
        existingCustomer.setMobileNumber("987654321");
        existingCustomer.setEmail("existing@example.com");
        existingCustomer.setUsername("existinguser");
        existingCustomer.setPassword("existingpassword");

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        mockMvc.perform(get("/customers/edit")
                        .param("id", String.valueOf(customerId))
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/customer-edit"))
                .andExpect(model().attribute("customers", Matchers.equalTo(Optional.of(existingCustomer))));
    }

    @Test
    public void shouldEditCustomer() throws Exception {
        Long customerId = 1L;
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setName("Existing Name");
        existingCustomer.setMobileNumber("987654321");
        existingCustomer.setEmail("existing@example.com");
        existingCustomer.setUsername("existinguser");
        existingCustomer.setPassword("existingpassword");

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        String updatedName = "Updated Name";
        String updatedMobileNumber = "123456789";
        String updatedEmail = "updated@example.com";

        mockMvc.perform(post("/customers/edit")
                        .param("id", String.valueOf(customerId))
                        .param("name", updatedName)
                        .param("mobileNumber", updatedMobileNumber)
                        .param("email", updatedEmail)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerRepository).save(customerCaptor.capture());
        Customer updatedCustomer = customerCaptor.getValue();

        assertEquals(updatedName, updatedCustomer.getName());
        assertEquals(updatedMobileNumber, updatedCustomer.getMobileNumber());
        assertEquals(updatedEmail, updatedCustomer.getEmail());
    }

    @Test
    public void shouldReturnCustomerDeleteView() throws Exception {
        Long customerId = 1L;
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setName("Existing Name");
        existingCustomer.setMobileNumber("987654321");
        existingCustomer.setEmail("existing@example.com");
        existingCustomer.setUsername("existinguser");
        existingCustomer.setPassword("existingpassword");

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        mockMvc.perform(get("/customers/delete")
                        .param("id", String.valueOf(customerId))
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/customer-delete"))
                .andExpect(model().attribute("customers", existingCustomer));
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        Long customerId = 1L;
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setName("Existing Name");
        existingCustomer.setMobileNumber("987654321");
        existingCustomer.setEmail("existing@example.com");
        existingCustomer.setUsername("existinguser");
        existingCustomer.setPassword("existingpassword");

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        Mockito.doNothing().when(customerService).deleteCustomer(customerId);

        mockMvc.perform(post("/customers/delete")
                        .param("id", String.valueOf(customerId))
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));

        Mockito.verify(customerService).deleteCustomer(customerId);
    }

}