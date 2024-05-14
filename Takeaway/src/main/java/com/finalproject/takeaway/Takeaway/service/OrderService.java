package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dto.Order;

import java.util.List;

/**
 * The OrderService interface provides methods to manage orders.
 */
public interface OrderService {
    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    List<Order> getAllOrders();

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return the order with the specified ID, or null if not found
     */
    Order getOrderById(int id);

    /**
     * Adds a new order.
     *
     * @param order the order to add
     * @return the added order
     */
    Order addNewOrder(Order order);

    /**
     * Updates the data of an existing order.
     *
     * @param order the order with updated data
     * @return the updated order
     */
    Order updateOrderData(Order order);

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     */
    void deleteOrder(int id);
}
