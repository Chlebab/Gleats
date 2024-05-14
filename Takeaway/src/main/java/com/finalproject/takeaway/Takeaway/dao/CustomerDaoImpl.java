package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dao.mappers.CustomerMapper;
import com.finalproject.takeaway.Takeaway.dto.Customer;
import com.finalproject.takeaway.Takeaway.exceptions.CustomDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao{

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for CustomerDaoImpl class.
     * @param jdbcTemplate the JdbcTemplate object used for database operations.
     */
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new customer in the database.
     * @param customer the Customer object to be created.
     * @return the created Customer object.
     */
    @Override
    public Customer createNewCustomer(Customer customer) throws CustomDataAccessException {
        try {
            customer.setCustomerPassword("NOT IMPLEMENTED");

            final String INSERT_CUSTOMER = "INSERT INTO customer(customerEmailAddress, customerPassword, customerPhoneNumber) VALUES(?, ?, ?)";
            jdbcTemplate.update(INSERT_CUSTOMER,
                    customer.getCustomerEmailAddress(),
                    customer.getCustomerPassword(),
                    customer.getCustomerPhoneNumber());

            final String GET_NEWEST = "SELECT MAX(customerId) FROM customer";
            customer.setCustomerId(jdbcTemplate.queryForObject(GET_NEWEST, new SingleColumnRowMapper<Integer>()));
        }  catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error creating new customer", ex);
        }
        return customer;
    }

    /**
     * Retrieves all customers from the database.
     * @return a list of Customer objects.
     */
    @Override
    public List<Customer> getAllCustomers() throws CustomDataAccessException {
        try {
            final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
            return jdbcTemplate.query(SELECT_ALL_CUSTOMERS, new CustomerMapper());
        }  catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error getting all customers", ex);
        }
    }

    /**
     * Finds a customer in the database by their ID.
     * @param id the ID of the customer to find.
     * @return the found Customer object.
     */
    @Override
    public Customer findCustomerById(int id) throws CustomDataAccessException {
        try {
            final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE customerId = ?";
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID, new CustomerMapper(), id);
        }  catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error finding customer by ID", ex);
        }
    }

    /**
     * Updates a customer in the database.
     * @param customer the Customer object to be updated.
     */
    @Override
    public void updateCustomer(Customer customer) throws CustomDataAccessException {
        try {
            customer.setCustomerPassword("NOT IMPLEMENTED");

            final String UPDATE_CUSTOMER = "UPDATE customer SET customerEmailAddress = ?, customerPassword = ?, customerPhoneNumber = ? WHERE customerId = ?";
            jdbcTemplate.update(UPDATE_CUSTOMER,
                    customer.getCustomerEmailAddress(),
                    customer.getCustomerPassword(),
                    customer.getCustomerPhoneNumber(),
                    customer.getCustomerId());
        }  catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error updating customer", ex);
        }
    }

    /**
     * Deletes a customer from the database by their ID.
     * @param id the ID of the customer to delete.
     */
    @Override
    public void deleteCustomer(int id) throws CustomDataAccessException {
        try {
            final String DELETE_ORDERITEMS_BY_ID = "DELETE FROM orderItems WHERE orderId IN (SELECT orderId FROM orders WHERE customerId = ?)";
            jdbcTemplate.update(DELETE_ORDERITEMS_BY_ID, id);


            final String DELETE_ORDERS_BY_ID = "DELETE FROM orders WHERE customerId = ?;";
            jdbcTemplate.update(DELETE_ORDERS_BY_ID, id);


            final String DELETE_CUSTOMER_BY_ID = "DELETE FROM customer WHERE customerId = ?";
            jdbcTemplate.update(DELETE_CUSTOMER_BY_ID, id);
        }  catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error deleting customer by ID", ex);
        }
    }
}
