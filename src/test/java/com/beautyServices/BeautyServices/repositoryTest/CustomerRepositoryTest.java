package com.beautyServices.BeautyServices.repositoryTest;

import com.beautyServices.BeautyServices.entity.Customer;
import com.beautyServices.BeautyServices.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    @Transactional
    void whenFindByUsername_thenReturnCustomer(){
        Customer customer = new Customer(null, "Test User", "1234567890", "test@example.com", "test user", "password", "USER", true);
        customerRepository.save(customer);

        Customer found = customerRepository.getByUsername("test user");

        Assertions.assertNotNull(found);
        Assertions.assertEquals(customer.getUsername(), found.getUsername());
    }
    @Test
    @Transactional
    void whenFindAll_thenReturnsAllCustomers(){
        Customer testCustomer1 = new Customer(null, "Test User", "1234567890", "test@example.com", "test user1", "password", "USER", true);
        Customer testCustomer2 = new Customer(null, "Test User", "1234567890", "test@example.com", "test user2", "password", "USER", true);
        customerRepository.save(testCustomer1);
        customerRepository.save(testCustomer2);

        List<Customer> customerList = customerRepository.findAll();

        Assertions.assertFalse(customerList.isEmpty(), "The list of customers should not be empty.");
        Assertions.assertTrue(customerList.size() >= 2, "There should be at least two customers.");
    }

    @Test
    @Transactional
    void whenFindById_thenReturnCustomer(){
        Customer customer = new Customer(null, "Customer Test", "1234567890", "test@example.com", "test user", "password", "USER", true);
        customer = customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(customer.getCustomerId());

        Assertions.assertTrue(foundCustomer.isPresent(),"The customer should be found.");
        Assertions.assertEquals(customer.getCustomerId(), foundCustomer.get().getCustomerId(), "The ID should be the same.");
    }

    @Test
    @Transactional
    void whenSave_thenCustomerIsCreated(){
        Customer newCustomer = new Customer(null, "Customer Test", "1234567890", "test@example.com", "test user", "password", "USER", true);
        Customer savedCustomer = customerRepository.save(newCustomer);

        Assertions.assertNotNull(savedCustomer.getCustomerId(), "The customer ID should not be null after saving");
        Assertions.assertEquals("Customer Test", savedCustomer.getName(), "The customer name should be New Customer");
        Assertions.assertEquals("1234567890", savedCustomer.getMobileNumber(), "The mobile number should be 1234567890");
        Assertions.assertEquals("test@example.com", savedCustomer.getEmail(), "The customer email should be test@example.com");
        Assertions.assertEquals("test user", savedCustomer.getUsername(), "The customer username should be test user");
        Assertions.assertEquals("password", savedCustomer.getPassword(), "The customer password should be password");
        Assertions.assertEquals("USER", savedCustomer.getRole(), "The customer role should be USER");
        Assertions.assertEquals(true, savedCustomer.getActive(), "The customer active should be true");

        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getCustomerId());
        Assertions.assertTrue(foundCustomer.isPresent(), "The customer should be available in the database");
    }

    @Test
    @Transactional
    void whenDelete_thenCustomerIsRemoved(){
        Customer customer = new Customer(null, "Customer Test", "1234567890", "test@example.com", "testuser", "password", "USER", true);
        customer = customerRepository.save(customer);
        customerRepository.delete(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(customer.getCustomerId());
        Assertions.assertFalse(foundCustomer.isPresent(), "The customer should be removed");
    }
}
