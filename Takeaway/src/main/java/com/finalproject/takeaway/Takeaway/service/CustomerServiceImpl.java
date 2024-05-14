package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.CustomerDao;
import com.finalproject.takeaway.Takeaway.dto.Customer;
import com.finalproject.takeaway.Takeaway.exceptions.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * This class implements the CustomerService interface and provides the implementation for various customer-related operations.
 */
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerDao customerDao;

    /**
     * Constructs a new CustomerServiceImpl object with the given CustomerDao.
     * @param customerDao The CustomerDao object to be used for data access.
     */
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Retrieves a list of all customers.
     * @return A list of Customer objects representing all the customers.
     */
    @Override
    public List<Customer> getAllCustomers() {
        try {
            return customerDao.getAllCustomers();
        } catch (DataAccessException ex) {
            throw new CustomerException("Error retrieving all customers", ex);
        }
    }

    /**
     * Retrieves a customer by their ID.
     * @param id The ID of the customer to retrieve.
     * @return The Customer object with the specified ID, or a default Customer object if not found.
     */
    @Override
    public Customer getCustomerById(int id) {
        try {
            Customer customer = customerDao.findCustomerById(id);
            if (customer == null) {
                throw new CustomerException("Customer with id " + id + " not found");
            }
            return customer;
        } catch (DataAccessException ex) {
            throw new CustomerException("Error retrieving customer with id " + id, ex);
        }
    }

    /**
     * Adds a new customer.
     * @param customer The Customer object representing the new customer to be added.
     * @return The added Customer object.
     */
    @Override
    public Customer addNewCustomer(Customer customer) {
        if (customer.getCustomerEmailAddress().isBlank()){
            throw new CustomerException("Email Address Blank, customer NOT added");
        } else {
            try {
                customerDao.createNewCustomer(customer);
            } catch (DataAccessException ex) {
                throw new CustomerException("Error adding new customer", ex);
            }
        }
        return customer;
    }

    /**
     * Updates the data of an existing customer.
     * @param id The ID of the customer to be updated.
     * @param customer The Customer object containing the updated data.
     * @return The updated Customer object.
     */
    @Override
    public Customer updateCustomerData(int id, Customer customer) {
        if (customer.getCustomerId() != id){
            throw new CustomerException("IDs do not match, customer NOT updated");
        } else {
            try {
                customerDao.updateCustomer(customer);
            } catch (DataAccessException ex) {
                throw new CustomerException("Error updating customer with id " + id, ex);
            }
        }
        return customer;
    }

    /**
     * Deletes a customer by their ID.
     * @param id The ID of the customer to be deleted.
     */
    @Override
    public void deleteCustomerById(int id) {
        try {
            customerDao.deleteCustomer(id);
        } catch (DataAccessException ex) {
            throw new CustomerException("Error deleting customer with id " + id, ex);
        }
    }
}
