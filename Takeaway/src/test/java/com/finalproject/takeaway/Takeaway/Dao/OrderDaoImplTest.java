package com.finalproject.takeaway.Takeaway.Dao;

import com.finalproject.takeaway.Takeaway.dao.OrderDaoImpl;
import com.finalproject.takeaway.Takeaway.dto.Order;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the OrderDaoImpl class.
 */
class OrderDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrderDaoImpl orderDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for creating a new order.
     */
    @Test
    void testCreateNewOrder() {
        // Arrange
        Order order = new Order();
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenReturn(1);

        // Act
        Order result = orderDao.createNewOrder(order);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getOrderId());
    }

    /**
     * Test case for retrieving all orders.
     */
    @Test
    void testGetAllOrders() {
        // Arrange
        List<Order> expected = Arrays.asList(new Order(), new Order());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expected);

        // Act
        List<Order> result = orderDao.getAllOrders();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for finding an order by its ID.
     */
    @Test
    void testFindOrderById() {
        // Arrange
        Order expected = new Order();
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), any())).thenReturn(expected);

        // Act
        Order result = orderDao.findOrderById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for updating an order.
     */
    @Test
    void testUpdateOrder() {
        // Arrange
        Order order = new Order();
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> orderDao.updateOrder(order));
    }

    /**
     * Test case for deleting an order.
     */
    @Test
    void testDeleteOrder() {
        // Arrange
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> orderDao.deleteOrder(1));
    }
}