package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.OrderDao;
import com.finalproject.takeaway.Takeaway.dto.Order;
import com.finalproject.takeaway.Takeaway.exceptions.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the OrderServiceImpl class.
 */
class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for getting all orders.
     */
    @Test
    void testGetAllOrders() {
        // Arrange
        List<Order> expected = Arrays.asList(new Order(), new Order());
        when(orderDao.getAllOrders()).thenReturn(expected);

        // Act
        List<Order> result = orderService.getAllOrders();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for getting an order by ID.
     */
    @Test
    void testGetOrderById() {
        // Arrange
        int id = 1;
        Order expected = new Order();
        when(orderDao.findOrderById(id)).thenReturn(expected);

        // Act
        Order result = orderService.getOrderById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for getting an order by ID when the order is not found.
     */
    @Test
    void testGetOrderById_Exception() {
        // Arrange
        int id = 1;
        Order expected = new Order();
        expected.setOrderId(-1);
        when(orderDao.findOrderById(id)).thenReturn(expected);

        // Act
        Order result = orderService.getOrderById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expected.getOrderId(), result.getOrderId());
    }

    /**
     * Test case for adding a new order.
     */
    @Test
    void testAddNewOrder() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        order.setBusinessId(1);
        when(orderDao.createNewOrder(order)).thenReturn(order);

        // Act
        Order result = orderService.addNewOrder(order);

        // Assert
        assertNotNull(result);
        assertEquals(order, result);
    }

    /**
     * Test case for adding a new order with missing customer ID.
     */
    @Test
    void testAddNewOrder_MissingCustomerId() {
        // Arrange
        Order order = new Order();
        order.setBusinessId(1);

        // Act
        Throwable exception = assertThrows(OrderException.class, () -> {
            Order result = orderService.addNewOrder(order);
        });

        // Assert
        assertEquals("Missing customer id", exception.getMessage());
    }

    /**
     * Test case for adding a new order with missing business ID.
     */
    @Test
    void testAddNewOrder_MissingBusinessId() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);

        // Act
        Throwable exception = assertThrows(OrderException.class, () -> {
            Order result = orderService.addNewOrder(order);
        });

        // Assert
        assertEquals("Missing business id", exception.getMessage());
    }

    /**
     * Test case for updating order data.
     */
    @Test
    void testUpdateOrderData() {
        // Arrange
        Order order = new Order();
        order.setOrderId(1);
        doNothing().when(orderDao).updateOrder(order);

        // Act
        Order result = orderService.updateOrderData(order);

        // Assert
        assertNotNull(result);
        assertEquals(order, result);
    }

    /**
     * Test case for updating order data with missing order ID.
     */
    @Test
    void testUpdateOrderData_MissingOrderId() {
        // Arrange
        Order order = new Order();

        // Act
        Throwable exception = assertThrows(OrderException.class, () -> {
            Order result = orderService.updateOrderData(order);
        });

        // Assert
        assertEquals("Missing order id", exception.getMessage());
    }

    /**
     * Test case for deleting an order.
     */
    @Test
    void testDeleteOrder() {
        // Arrange
        int id = 1;

        // Act
        orderService.deleteOrder(id);

        // Assert
        verify(orderDao, times(1)).deleteOrder(id);
    }
}