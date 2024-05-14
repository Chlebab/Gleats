package com.finalproject.takeaway.Takeaway.dto;

import java.sql.Timestamp;

/**
 * The Order class represents an order made by a customer for a specific business.
 */
public class Order {
    private int orderId;
    private int customerId;
    private int businessId;
    private Timestamp orderTime;

    /**
     * Gets the order ID.
     *
     * @return The order ID.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the order ID.
     *
     * @param orderId The order ID to set.
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the business ID.
     *
     * @return The business ID.
     */
    public int getBusinessId() {
        return businessId;
    }

    /**
     * Sets the business ID.
     *
     * @param businessId The business ID to set.
     */
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    /**
     * Gets the order time.
     *
     * @return The order time.
     */
    public Timestamp getOrderTime() {
        return orderTime;
    }

    /**
     * Sets the order time.
     *
     * @param orderTime The order time to set.
     */
    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
}
