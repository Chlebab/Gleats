/**
    * Maps a row from the ResultSet to a Order object.
    *
    * @param rs     the ResultSet containing the data from the database
    * @param rowNum the row number
    * @return the mapped Order object
    * @throws SQLException if a database access error occurs
    */
package com.finalproject.takeaway.Takeaway.dao.mappers;

import com.finalproject.takeaway.Takeaway.dto.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        Order order = new Order();
        order.setOrderId(rs.getInt("orderId"));
        order.setCustomerId(rs.getInt("customerId"));
        order.setBusinessId(rs.getInt("businessId"));
        order.setOrderTime(rs.getTimestamp("orderTime"));
        return order;

        //YOUR CODE ENDS HERE
    }
}
