package com.finalproject.takeaway.Takeaway.dto;

/**
 * The MenuItem class represents a menu item in a restaurant's menu.
 */
public class MenuItem {
    private int itemId;
    private String itemName;
    private int businessId;

    /**
     * Gets the ID of the menu item.
     *
     * @return The ID of the menu item.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the ID of the menu item.
     *
     * @param itemId The ID of the menu item.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the name of the menu item.
     *
     * @return The name of the menu item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param itemName The name of the menu item.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the ID of the business that offers the menu item.
     *
     * @return The ID of the business.
     */
    public int getBusinessId() {
        return businessId;
    }

    /**
     * Sets the ID of the business that offers the menu item.
     *
     * @param businessId The ID of the business.
     */
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }
}
