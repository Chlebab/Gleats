package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.OrderItemsDao;
import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import com.finalproject.takeaway.Takeaway.exceptions.OrderItemsException;
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
 * Unit tests for the OrderItemsServiceImpl class.
 */
class OrderItemsServiceImplTest {

    @Mock
    private OrderItemsDao orderItemsDao;

    @InjectMocks
    private OrderItemsServiceImpl orderItemsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the getAllOrderItems method.
     */
    @Test
    void testGetAllOrderItems() {
        // Arrange
        List<OrderItems> expected = Arrays.asList(new OrderItems(), new OrderItems());
        when(orderItemsDao.getAllOrderItems()).thenReturn(expected);

        // Act
        List<OrderItems> result = orderItemsService.getAllOrderItems();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for the getOrderItemsById method.
     */
    @Test
    void testGetOrderItemsById() {
        // Arrange
        int id = 1;
        OrderItems expected = new OrderItems();
        when(orderItemsDao.findOrderItemsById(id)).thenReturn(expected);

        // Act
        OrderItems result = orderItemsService.getOrderItemsById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for the getOrderItemsById method when an exception is thrown.
     */
    @Test
    void testGetOrderItemsById_Exception() {
        // Arrange
        int id = 1;
        OrderItems expected = new OrderItems();
        expected.setOrderId(-1);
        expected.setItemId(-1);
        when(orderItemsDao.findOrderItemsById(id)).thenThrow(new RuntimeException());

        // Act
        Throwable exception = assertThrows(OrderItemsException.class, () -> {
            OrderItems result = orderItemsService.getOrderItemsById(id);
        });

        // Assert
        assertEquals("Error retrieving order items with id 1", exception.getMessage());
    }

    /**
     * Test case for the addNewOrderItems method.
     */
    @Test
    void testAddNewOrderItems() {
        // Arrange
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(1);
        orderItems.setItemId(1);
        when(orderItemsDao.createNewOrderItems(orderItems)).thenReturn(orderItems);

        // Act
        OrderItems result = orderItemsService.addNewOrderItems(orderItems);

        // Assert
        assertNotNull(result);
        assertEquals(orderItems, result);
    }

    /**
     * Test case for the addNewOrderItems method when the orderId is missing.
     */
    @Test
    void testAddNewOrderItems_MissingOrderId() {
        // Arrange
        OrderItems orderItems = new OrderItems();
        orderItems.setItemId(1);

        // Act
        Throwable exception = assertThrows(OrderItemsException.class, () -> {
            OrderItems result = orderItemsService.addNewOrderItems(orderItems);
        });

        // Assert
        assertEquals("Missing order id", exception.getMessage());
    }

    /**
     * Test case for the addNewOrderItems method when the itemId is missing.
     */
    @Test
    void testAddNewOrderItems_MissingItemId() {
        // Arrange
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(1);

        // Act
        Throwable exception = assertThrows(OrderItemsException.class, () -> {
            OrderItems result = orderItemsService.addNewOrderItems(orderItems);
        });

        // Assert
        assertEquals("Missing item id", exception.getMessage());
    }

    /**
     * Test case for the updateOrderItemsData method.
     */
    @Test
    void testUpdateOrderItemsData() {
        // Arrange
        int id = 1;
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemsId(id);
        doNothing().when(orderItemsDao).updateOrderItems(orderItems);

        // Act
        OrderItems result = orderItemsService.updateOrderItemsData(id, orderItems);

        // Assert
        assertNotNull(result);
        assertEquals(orderItems, result);
    }

    /**
     * Test case for the updateOrderItemsData method when the orderItemsId is mismatched.
     */
    @Test
    void testUpdateOrderItemsData_MismatchedId() {
        // Arrange
        int id = 1;
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemsId(2);

        // Act
        Throwable exception = assertThrows(OrderItemsException.class, () -> {
            OrderItems result = orderItemsService.updateOrderItemsData(id, orderItems);
        });

        // Assert
        assertEquals("IDs do not match, order items not updated", exception.getMessage());
    }

    /**
     * Test case for the deleteOrderItemsById method.
     */
    @Test
    void testDeleteOrderItemsById() {
        // Arrange
        int id = 1;

        // Act
        orderItemsService.deleteOrderItemsById(id);

        // Assert
        verify(orderItemsDao, times(1)).deleteOrderItems(id);
    }
}