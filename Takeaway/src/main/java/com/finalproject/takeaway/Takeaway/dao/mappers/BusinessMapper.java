/**
    * Maps a row from the ResultSet to a Business object.
    *
    * @param rs     the ResultSet containing the data from the database
    * @param rowNum the row number
    * @return the mapped Business object
    * @throws SQLException if a database access error occurs
    */
package com.finalproject.takeaway.Takeaway.dao.mappers;

import com.finalproject.takeaway.Takeaway.dto.Business;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessMapper implements RowMapper<Business> {
   
    @Override
    public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        Business business = new Business();
        business.setBusinessId(rs.getInt("businessId"));
        business.setBusinessName(rs.getString("businessName"));
        business.setCuisine(rs.getString("cuisine"));
    
        return business;

        //YOUR CODE ENDS HERE
    }
    
}
