package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import java.util.List;

/**
 * The OrderItemsService interface provides methods to manage order items.
 */
public interface OrderItemsService {
    
    /**
     * Retrieves all order items.
     *
     * @return a list of all order items
     */
    List<OrderItems> getAllOrderItems();
    
    /**
     * Retrieves an order item by its ID.
     *
     * @param id the ID of the order item to retrieve
     * @return the order item with the specified ID, or null if not found
     */
    OrderItems getOrderItemsById(int id);
    
    /**
     * Adds a new order item.
     *
     * @param orderItems the order item to add
     * @return the added order item
     */
    OrderItems addNewOrderItems(OrderItems orderItems);
    
    /**
     * Updates the data of an existing order item.
     *
     * @param id the ID of the order item to update
     * @param orderItems the updated order item data
     * @return the updated order item
     */
    OrderItems updateOrderItemsData(int id, OrderItems orderItems);
    
    /**
     * Deletes an order item by its ID.
     *
     * @param id the ID of the order item to delete
     */
    void deleteOrderItemsById(int id);
}
