package com.finalproject.takeaway.Takeaway.Dao;

import com.finalproject.takeaway.Takeaway.dao.OrderItemsDaoImpl;
import com.finalproject.takeaway.Takeaway.dto.OrderItems;
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
 * Unit tests for the OrderItemsDaoImpl class.
 */
class OrderItemsDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrderItemsDaoImpl orderItemsDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for creating a new order item.
     */
    @Test
    void testCreateNewOrderItems() {
        // Arrange
        OrderItems orderItems = new OrderItems();
        when(jdbcTemplate.update(anyString(), any(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenReturn(1);

        // Act
        OrderItems result = orderItemsDao.createNewOrderItems(orderItems);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getOrderItemsId());
    }

    /**
     * Test case for retrieving all order items.
     */
    @Test
    void testGetAllOrderItems() {
        // Arrange
        List<OrderItems> expected = Arrays.asList(new OrderItems(), new OrderItems());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expected);

        // Act
        List<OrderItems> result = orderItemsDao.getAllOrderItems();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for finding an order item by its ID.
     */
    @Test
    void testFindOrderItemsById() {
        // Arrange
        OrderItems expected = new OrderItems();
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), any())).thenReturn(expected);

        // Act
        OrderItems result = orderItemsDao.findOrderItemsById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for updating an order item.
     */
    @Test
    void testUpdateOrderItems() {
        // Arrange
        OrderItems orderItems = new OrderItems();
        when(jdbcTemplate.update(anyString(), any(), any())).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> orderItemsDao.updateOrderItems(orderItems));
    }

    /**
     * Test case for deleting an order item.
     */
    @Test
    void testDeleteOrderItems() {
        // Arrange
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> orderItemsDao.deleteOrderItems(1));
    }
}