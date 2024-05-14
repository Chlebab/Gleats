package com.finalproject.takeaway.Takeaway.dto;

/**
 * The Customer class represents a customer in the system.
 */
public class Customer {
    private int customerId;
    private String customerEmailAddress;
    private String customerPassword;
    private String customerPhoneNumber;

    /**
     * Returns the customer ID.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns the customer's email address.
     *
     * @return the customer's email address
     */
    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    /**
     * Sets the customer's email address.
     *
     * @param customerEmailAddress the customer's email address to set
     */
    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    /**
     * Returns the customer's password.
     *
     * @return the customer's password
     */
    public String getCustomerPassword() {
        return customerPassword;
    }

    /**
     * Sets the customer's password.
     *
     * @param customerPassword the customer's password to set
     */
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return the customer's phone number
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param customerPhoneNumber the customer's phone number to set
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}
