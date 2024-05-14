/**
    * Maps a row from the ResultSet to a MenuItem object.
    *
    * @param rs     the ResultSet containing the data from the database
    * @param rowNum the row number
    * @return the mapped MenuItem object
    * @throws SQLException if a database access error occurs
    */
package com.finalproject.takeaway.Takeaway.dao.mappers;

import com.finalproject.takeaway.Takeaway.dto.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customerId"));
        customer.setCustomerEmailAddress(rs.getString("customerEmailAddress"));
        customer.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
        return customer;

        //YOUR CODE ENDS HERE
    }
}
