package com.finalproject.takeaway.Takeaway.dto;

/**
 * The OrderItems class represents the items included in an order.
 */
public class OrderItems {
    private int orderItemsId;
    private int orderId;
    private int itemId;

    /**
     * Returns the order items ID.
     *
     * @return the order items ID
     */
    public int getOrderItemsId() {
        return orderItemsId;
    }

    /**
     * Sets the order items ID.
     *
     * @param orderItemsId the order items ID to set
     */
    public void setOrderItemsId(int orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    /**
     * Returns the order ID.
     *
     * @return the order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the order ID.
     *
     * @param orderId the order ID to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns the item ID.
     *
     * @return the item ID
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the item ID.
     *
     * @param itemId the item ID to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
