package com.finalproject.takeaway.Takeaway.Dao;

import com.finalproject.takeaway.Takeaway.dao.CustomerDao;
import com.finalproject.takeaway.Takeaway.dao.CustomerDaoImpl;
import com.finalproject.takeaway.Takeaway.dao.mappers.CustomerMapper;
import com.finalproject.takeaway.Takeaway.dto.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the CustomerDaoImpl class.
 */
class CustomerDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerDaoImpl customerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for creating a new customer.
     */
    @Test
    void testCreateNewCustomer() {
        // Arrange
        Customer customer = new Customer();
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenReturn(1);

        // Act
        Customer result = customerDao.createNewCustomer(customer);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCustomerId());
    }

    /**
     * Test case for retrieving all customers.
     */
    @Test
    void testGetAllCustomers() {
        // Arrange
        List<Customer> expected = Arrays.asList(new Customer(), new Customer());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expected);

        // Act
        List<Customer> result = customerDao.getAllCustomers();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for finding a customer by ID.
     */
    @Test
    void testFindCustomerById() {
        // Arrange
        Customer expected = new Customer();
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), any())).thenReturn(expected);

        // Act
        Customer result = customerDao.findCustomerById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for updating a customer.
     */
    @Test
    void testUpdateCustomer() {
        // Arrange
        Customer customer = new Customer();
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> customerDao.updateCustomer(customer));
    }

    /**
     * Test case for deleting a customer.
     */
    @Test
    void testDeleteCustomer() {
        // Arrange
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> customerDao.deleteCustomer(1));
    }
}