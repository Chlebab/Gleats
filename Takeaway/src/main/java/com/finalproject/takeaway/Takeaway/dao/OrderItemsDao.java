package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * The OrderItemsDao interface provides methods to interact with the order items data in the database.
 */
public interface OrderItemsDao {
    /**
     * Creates a new order item in the database.
     *
     * @param orderItems The order item to be created.
     * @return The created order item.
     */
    OrderItems createNewOrderItems(OrderItems orderItems) throws DataAccessException;

    /**
     * Retrieves all order items from the database.
     *
     * @return A list of all order items.
     */
    List<OrderItems> getAllOrderItems() throws DataAccessException;

    /**
     * Finds an order item by its ID in the database.
     *
     * @param id The ID of the order item to find.
     * @return The found order item, or null if not found.
     */
    OrderItems findOrderItemsById(int id) throws DataAccessException;

    /**
     * Updates an existing order item in the database.
     *
     * @param orderItems The order item to be updated.
     */
    void updateOrderItems(OrderItems orderItems) throws DataAccessException;

    /**
     * Deletes an order item from the database.
     *
     * @param id The ID of the order item to delete.
     */
    void deleteOrderItems(int id) throws DataAccessException;
}
