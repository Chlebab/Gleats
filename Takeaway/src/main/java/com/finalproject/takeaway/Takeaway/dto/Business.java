package com.finalproject.takeaway.Takeaway.dto;

/**
 * The Business class represents a business entity.
 */
public class Business {
    private int businessId;
    private String BusinessName;
    private String Cuisine;

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
     * Gets the business name.
     *
     * @return The business name.
     */
    public String getBusinessName() {
        return BusinessName;
    }

    /**
     * Sets the business name.
     *
     * @param businessName The business name to set.
     */
    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    /**
     * Gets the cuisine.
     *
     * @return The cuisine.
     */
    public String getCuisine() {
        return Cuisine;
    }

    /**
     * Sets the cuisine.
     *
     * @param cuisine The cuisine to set.
     */
    public void setCuisine(String cuisine) {
        Cuisine = cuisine;
    }
}
