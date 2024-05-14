package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.OrderItemsDao;
import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import com.finalproject.takeaway.Takeaway.exceptions.OrderItemsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService{
    @Autowired
    private final OrderItemsDao orderItemsDao;

    /**
     * This class represents the implementation of the OrderItemsService interface.
     * It provides methods to interact with the OrderItemsDao to perform CRUD operations on OrderItems.
     *
     * @param orderItemsDao the data access object for OrderItems
     */
    public OrderItemsServiceImpl(OrderItemsDao orderItemsDao) {
        this.orderItemsDao = orderItemsDao;
    }

    /**
     * Retrieves all order items.
     *
     * @return a list of OrderItems objects representing all order items.
     */
    @Override
    public List<OrderItems> getAllOrderItems() {
        try {
            return orderItemsDao.getAllOrderItems();
        } catch (Exception ex) {
            throw new OrderItemsException("Error retrieving all order items", ex);
        }
    }

    /**
     * Retrieves an order item by its ID.
     *
     * @param id the ID of the order item to retrieve
     * @return the OrderItems object representing the order item with the specified ID,
     *         or a new OrderItems object with -1 values if the order item is not found
     */
    @Override
    public OrderItems getOrderItemsById(int id) {
        try {
            OrderItems orderItems = orderItemsDao.findOrderItemsById(id);
            if (orderItems == null) {
                throw new OrderItemsException("Order items with id " + id + " not found");
            }
            return orderItems;
        } catch (Exception e) {
            throw new OrderItemsException("Error retrieving order items with id " + id, e);
        }
    }

    /**
     * Adds a new order item.
     *
     * @param orderItems the OrderItems object representing the order item to add
     * @return the added OrderItems object if the order item is valid,
     *         or a new OrderItems object with -1 values if the order item is not valid
     */
    @Override
    public OrderItems addNewOrderItems(OrderItems orderItems) {
        if (orderItems.getOrderId() == 0) {
            throw new OrderItemsException("Missing order id");
        }
        else if(orderItems.getItemId() == 0){
            throw new OrderItemsException("Missing item id");
        }
        try {
            return orderItemsDao.createNewOrderItems(orderItems);
        } catch (Exception ex) {
            throw new OrderItemsException("Error adding new order items", ex);
        }
    }

    /**
     * Updates an existing order item.
     *
     * @param id the ID of the order item to update
     * @param orderItems the OrderItems object representing the updated order item data
     * @return the updated OrderItems object if the update is successful,
     *         or a new OrderItems object with -1 values if the update fails
     */
    @Override
    public OrderItems updateOrderItemsData(int id, OrderItems orderItems) {
        if (id != orderItems.getOrderItemsId()) {
            throw new OrderItemsException("IDs do not match, order items not updated");
        }
        try {
            orderItemsDao.updateOrderItems(orderItems);
        } catch (Exception ex) {
            throw new OrderItemsException("Error updating order items with id " + id, ex);
        }
        return orderItems;
    }

    /**
     * Deletes an order item by its ID.
     *
     * @param id the ID of the order item to delete
     */
    @Override
    public void deleteOrderItemsById(int id) {
        try {
            orderItemsDao.deleteOrderItems(id);
        } catch (Exception ex) {
            throw new OrderItemsException("Error deleting order items with id " + id, ex);
        }
    }
}
