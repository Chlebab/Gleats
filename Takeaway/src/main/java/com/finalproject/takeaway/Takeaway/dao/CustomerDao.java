package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dto.Business;
import com.finalproject.takeaway.Takeaway.dto.Customer;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * The CustomerDao interface provides methods to interact with the customer data in the database.
 */
public interface CustomerDao {
    
    /**
     * Creates a new customer in the database.
     *
     * @param customer The customer object to be created.
     * @return The created customer object.
     */
    Customer createNewCustomer(Customer customer) throws DataAccessException;
    
    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers.
     */
    List<Customer> getAllCustomers() throws DataAccessException;
    
    /**
     * Finds a customer by their ID in the database.
     *
     * @param id The ID of the customer to find.
     * @return The customer object if found, null otherwise.
     */
    Customer findCustomerById(int id) throws DataAccessException;
    
    /**
     * Updates the details of a customer in the database.
     *
     * @param customer The customer object with updated details.
     */
    void updateCustomer(Customer customer) throws DataAccessException;
    
    /**
     * Deletes a customer from the database.
     *
     * @param id The ID of the customer to delete.
     */
    void deleteCustomer(int id) throws DataAccessException;
}
