package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dto.Customer;

import java.util.List;

/**
 * The CustomerService interface provides methods to manage customer data.
 */
public interface CustomerService {
    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers
     */
    List<Customer> getAllCustomers();

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the customer with the specified ID, or null if not found
     */
    Customer getCustomerById(int id);

    /**
     * Adds a new customer.
     *
     * @param customer the customer to add
     * @return the added customer
     */
    Customer addNewCustomer(Customer customer);

    /**
     * Updates the data of an existing customer.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated customer data
     * @return the updated customer
     */
    Customer updateCustomerData(int id, Customer customer);

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     */
    void deleteCustomerById(int id);
}
