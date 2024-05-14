package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dto.Order;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * The OrderDao interface provides methods to interact with the order data in the system.
 */
public interface OrderDao {

    /**
     * Creates a new order in the system.
     *
     * @param order The order to be created.
     * @return The created order.
     */
    Order createNewOrder(Order order) throws DataAccessException;

    /**
     * Retrieves all orders from the system.
     *
     * @return A list of all orders.
     */
    List<Order> getAllOrders() throws DataAccessException;

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order to find.
     * @return The found order, or null if not found.
     */
    Order findOrderById(int id) throws DataAccessException;

    /**
     * Updates an existing order in the system.
     *
     * @param order The order to be updated.
     */
    void updateOrder(Order order) throws DataAccessException;

    /**
     * Deletes an order from the system.
     *
     * @param id The ID of the order to delete.
     */
    void deleteOrder(int id) throws DataAccessException;
}
