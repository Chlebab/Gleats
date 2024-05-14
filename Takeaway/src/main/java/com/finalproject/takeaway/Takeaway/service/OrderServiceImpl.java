package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.OrderDao;
import com.finalproject.takeaway.Takeaway.dto.Order;
import com.finalproject.takeaway.Takeaway.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the OrderService interface and provides the implementation for various order-related operations.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final OrderDao orderDao;

    /**
     * Constructs a new OrderServiceImpl object with the specified OrderDao.
     * @param orderDao The OrderDao object to be used for database operations.
     */
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Retrieves all orders from the database.
     * @return A list of Order objects representing all the orders.
     */
    @Override
    public List<Order> getAllOrders() {
        try {
            return orderDao.getAllOrders();
        } catch (Exception ex) {
            throw new OrderException("Error retrieving all orders", ex);
        }
    }

    /**
     * Retrieves an order by its ID from the database.
     * @param id The ID of the order to retrieve.
     * @return The Order object with the specified ID, or an Order object with orderId set to -1 if the order is not found.
     */
    @Override
    public Order getOrderById(int id) {
        try {
            Order order = orderDao.findOrderById(id);
            if (order == null) {
                throw new OrderException("Order with id " + id + " not found");
            }
            return order;
        } catch (Exception e) {
            throw new OrderException("Error retrieving order with id " + id, e);
        }
    }

    /**
     * Adds a new order to the database.
     * @param order The Order object representing the new order to be added.
     * @return The Order object that was added, or an Order object with orderId set to -1 if the order is invalid.
     */
    @Override
    public Order addNewOrder(Order order) {
        if (order.getCustomerId() == 0) {
            throw new OrderException("Missing customer id");
        }
        else if(order.getBusinessId() == 0){
            throw new OrderException("Missing business id");
        }
        try {
            return orderDao.createNewOrder(order);
        } catch (Exception ex) {
            throw new OrderException("Error adding new order", ex);
        }
    }

    /**
     * Updates the data of an existing order in the database.
     * @param order The Order object representing the updated order data.
     * @return The Order object that was updated, or an Order object with orderId set to -1 if the order is invalid.
     */
    @Override
    public Order updateOrderData(Order order) {
        if (order.getOrderId() == 0) {
            throw new OrderException("Missing order id");
        }
        try {
            orderDao.updateOrder(order);
        } catch (Exception ex) {
            throw new OrderException("Error updating order with id " + order.getOrderId(), ex);
        }
        return order;
    }

    /**
     * Deletes an order from the database.
     * @param id The ID of the order to delete.
     */
    @Override
    public void deleteOrder(int id) {
        try {
            orderDao.deleteOrder(id);
        } catch (Exception ex) {
            throw new OrderException("Error deleting order with id " + id, ex);
        }
    }
}
