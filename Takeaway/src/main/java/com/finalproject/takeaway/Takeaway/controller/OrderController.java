package com.finalproject.takeaway.Takeaway.controller;

import com.finalproject.takeaway.Takeaway.dto.Order;
import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import com.finalproject.takeaway.Takeaway.exceptions.OrderException;
import com.finalproject.takeaway.Takeaway.exceptions.OrderItemsException;
import com.finalproject.takeaway.Takeaway.service.OrderItemsServiceImpl;
import com.finalproject.takeaway.Takeaway.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

/**
 * The OrderController class handles HTTP requests related to orders.
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    OrderItemsServiceImpl orderItemsServiceImpl;

    /**
     * Creates a new order based on the provided payload.
     *
     * @param payload The request body containing the customer ID, business ID, and order items list.
     */
    @PostMapping("/create")
    public void createOrder(@RequestBody Map<String, Object> payload) {
        try {
            int customerId = (int) payload.get("customerId");
            int businessId = (int) payload.get("businessId");
            List<List<Integer>> orderItemsList = (List<List<Integer>>) payload.get("orderItemsList");

            // Create a new order
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setBusinessId(businessId);
            // Set orderTime to current time
            order.setOrderTime(new java.sql.Timestamp(new java.util.Date().getTime()));
            order = orderServiceImpl.addNewOrder(order);

            // For each item in the orderItemsList, create new OrderItems
            for (List<Integer> item : orderItemsList) {
                int itemId = item.get(0);
                int quantity = item.get(1);

                for (int i = 0; i < quantity; i++) {
                    OrderItems orderItems = new OrderItems();
                    orderItems.setOrderId(order.getOrderId());
                    orderItems.setItemId(itemId);
                    orderItemsServiceImpl.addNewOrderItems(orderItems);
                }
            }
        } catch (OrderException | OrderItemsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }
}
