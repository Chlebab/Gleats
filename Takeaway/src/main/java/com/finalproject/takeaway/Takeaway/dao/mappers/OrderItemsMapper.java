/**
    * Maps a row from the ResultSet to a OrderItems object.
    *
    * @param rs     the ResultSet containing the data from the database
    * @param rowNum the row number
    * @return the mapped OrderItems object
    * @throws SQLException if a database access error occurs
    */
package com.finalproject.takeaway.Takeaway.dao.mappers;

import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemsMapper implements RowMapper<OrderItems> {

    @Override
    public OrderItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemsId(rs.getInt("orderItemsId"));
        orderItems.setOrderId(rs.getInt("orderId"));
        orderItems.setItemId(rs.getInt("itemId"));
        return orderItems;

        //YOUR CODE ENDS HERE
    }
}
