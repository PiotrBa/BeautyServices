package com.piotrba.beautyservices.repositoryTest;

import com.piotrba.beautyservices.entity.Customer;
import com.piotrba.beautyservices.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static com.piotrba.beautyservices.UtilsData.builderCustomer;


@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void whenFindByUsername_thenReturnCustomer(){
        Customer customer = builderCustomer();
        customerRepository.save(customer);

        Customer found = customerRepository.getByUsername("test user");

        Assertions.assertNotNull(found);
        assertEquals(customer.getUsername(), found.getUsername());
    }
    @Test
    void whenFindAll_thenReturnsAllCustomers(){
        Customer customer1 = builderCustomer();
        customer1.setUsername("test user1");
        Customer customer2 = builderCustomer();
        customer2.setUsername("test user2");
        customerRepository.saveAll(List.of(customer1, customer2));

        List<Customer> customerList = customerRepository.findAll();

        assertFalse(customerList.isEmpty(), "The list of customers should not be empty.");
        assertTrue(customerList.size() >= 2, "There should be at least two customers.");
    }

    @Test
    void whenFindById_thenReturnCustomer(){
        Customer customer = builderCustomer();
        customer = customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(customer.getCustomerId());

        assertTrue(foundCustomer.isPresent(),"The customer should be found.");
        assertEquals(customer.getCustomerId(), foundCustomer.get().getCustomerId(), "The ID should be the same.");
    }

    @Test
    void whenSave_thenCustomerIsCreated(){
        Customer newCustomer = builderCustomer();
        Customer savedCustomer = customerRepository.save(newCustomer);

        Assertions.assertNotNull(savedCustomer.getCustomerId(), "The customer ID should not be null after saving");
        assertEquals("Customer Test", savedCustomer.getName(), "The customer name should be New Customer");
        assertEquals("1234567890", savedCustomer.getMobileNumber(), "The mobile number should be 1234567890");
        assertEquals("test@example.com", savedCustomer.getEmail(), "The customer email should be test@example.com");
        assertEquals("test user", savedCustomer.getUsername(), "The customer username should be test user");
        assertEquals("password", savedCustomer.getPassword(), "The customer password should be password");
        assertEquals("USER", savedCustomer.getRole(), "The customer role should be USER");
        assertEquals(true, savedCustomer.getActive(), "The customer active should be true");

        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getCustomerId());
        assertTrue(foundCustomer.isPresent(), "The customer should be available in the database");
    }

    @Test
    void whenDelete_thenCustomerIsRemoved(){
        Customer customer = builderCustomer();
        customer = customerRepository.save(customer);
        customerRepository.delete(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(customer.getCustomerId());
        assertFalse(foundCustomer.isPresent(), "The customer should be removed");
    }
}
