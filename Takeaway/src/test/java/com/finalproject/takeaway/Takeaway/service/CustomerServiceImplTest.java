package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.CustomerDao;
import com.finalproject.takeaway.Takeaway.dto.Customer;
import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the CustomerServiceImpl class.
 */
class CustomerServiceImplTest {
    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the getAllCustomers() method.
     * It verifies that the getAllCustomers() method returns the expected list of customers.
     */
    @Test
    void testGetAllCustomers() {
        // Arrange
        List<Customer> expected = Arrays.asList(new Customer(), new Customer());
        when(customerDao.getAllCustomers()).thenReturn(expected);

        // Act
        List<Customer> result = customerService.getAllCustomers();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for the getCustomerById() method.
     * It verifies that the getCustomerById() method returns the expected customer.
     */
    @Test
    void testGetCustomerById() {
        // Arrange
        Customer expected = new Customer();
        when(customerDao.findCustomerById(anyInt())).thenReturn(expected);

        // Act
        Customer result = customerService.getCustomerById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for the addNewCustomer() method.
     * It verifies that the addNewCustomer() method returns the added customer.
     */
    @Test
    void testAddNewCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerEmailAddress("Test Customer"); // Set a customer email address
        when(customerDao.createNewCustomer(any(Customer.class))).thenReturn(customer);

        // Act
        Customer result = customerService.addNewCustomer(customer);

        // Assert
        assertNotNull(result);
        assertEquals(customer, result);
    }

    /**
     * Test case for the updateCustomerData() method.
     * It verifies that the updateCustomerData() method returns the updated customer.
     */
    @Test
    void testUpdateCustomerData() {
        // Arrange
        int id = 1;
        Customer customer = new Customer();
        customer.setCustomerId(id);
        doNothing().when(customerDao).updateCustomer(customer);

        // Act
        Customer result = customerService.updateCustomerData(id, customer);

        // Assert
        assertNotNull(result);
        assertEquals(customer, result);
    }
















    /**
     * Test case for the deleteCustomerById() method.
     * It verifies that the deleteCustomerById() method does not throw any exception.
     */
    @Test
    void testDeleteCustomerById() {
        // Arrange
        // No return value to capture

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> customerService.deleteCustomerById(1));
    }
}