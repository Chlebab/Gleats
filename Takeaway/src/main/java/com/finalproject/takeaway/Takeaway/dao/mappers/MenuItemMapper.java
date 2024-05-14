/**
    * Maps a row of the ResultSet to a MenuItem object.
    *
    * @param rs     the ResultSet containing the data from the database
    * @param rowNum the current row number
    * @return the mapped MenuItem object
    * @throws SQLException if a database access error occurs
    */
package com.finalproject.takeaway.Takeaway.dao.mappers;

import com.finalproject.takeaway.Takeaway.dto.MenuItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItemMapper implements RowMapper<MenuItem> {


    @Override
    public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        MenuItem menuItem = new MenuItem();
        menuItem.setItemId(rs.getInt("itemId"));
        menuItem.setItemName(rs.getString("itemName"));
        menuItem.setBusinessId(rs.getInt("businessId"));
        return menuItem;

        //YOUR CODE ENDS HERE
    }
}
