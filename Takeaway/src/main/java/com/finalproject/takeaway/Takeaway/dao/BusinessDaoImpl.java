package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dao.mappers.BusinessMapper;
import com.finalproject.takeaway.Takeaway.dto.Business;

import com.finalproject.takeaway.Takeaway.exceptions.CustomDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessDaoImpl implements BusinessDao{

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for BusinessDaoImpl class.
     * @param jdbcTemplate the JdbcTemplate object used for database operations.
     */
    public BusinessDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new business in the database.
     * @param business the Business object to be created.
     * @return the created Business object with the generated businessId.
     */
    @Override
    public Business createNewBusiness(Business business) throws CustomDataAccessException {
        try {
            final String INSERT_BUSINESS = "INSERT INTO business(businessName, cuisine) VALUES(?, ?)";
            jdbcTemplate.update(INSERT_BUSINESS,
                    business.getBusinessName(),
                    business.getCuisine());

            final String GET_NEWEST = "SELECT MAX(businessId) FROM business";
            business.setBusinessId(jdbcTemplate.queryForObject(GET_NEWEST, new SingleColumnRowMapper<Integer>()));
        } catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error creating new business", ex);
        }

        return business;
    }

    /**
     * Retrieves all businesses from the database.
     * @return a list of all Business objects.
     */
    @Override
    public List<Business> getAllBusinesses() throws CustomDataAccessException {
        try {
            final String SELECT_ALL_BUSINESS = "SELECT * FROM business";
            return jdbcTemplate.query(SELECT_ALL_BUSINESS, new BusinessMapper());
        } catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error retrieving all businesses", ex);
        }
    }

    /**
     * Finds a business in the database by its id.
     * @param id the id of the business to find.
     * @return the Business object with the specified id.
     */
    @Override
    public Business findBusinessById(int id) throws CustomDataAccessException {
        try {
            final String SELECT_BUSINESS_BY_ID = "SELECT * FROM business WHERE businessId = ?";
            return jdbcTemplate.queryForObject(SELECT_BUSINESS_BY_ID, new BusinessMapper(), id);
        } catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error finding all businesses by ID", ex);
        }
    }

    /**
     * Updates a business in the database.
     * @param business the Business object to be updated.
     */
    @Override
    public void updateBusiness(Business business) throws CustomDataAccessException {
        try {
            final String UPDATE_BUSINESS = "UPDATE business SET businessName = ?, cuisine = ? WHERE businessId = ?";
            jdbcTemplate.update(UPDATE_BUSINESS,
                    business.getBusinessName(),
                    business.getCuisine(),
                    business.getBusinessId());
        } catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error updating business by ID", ex);
        }
    }

    /**
     * Deletes a business from the database by its id.
     * @param id the id of the business to delete.
     */
    @Override
    public void deleteBusiness(int id) throws CustomDataAccessException {
        try {
            final String DELETE_ORDER_ITEMS_BY_ITEM_ID = "DELETE FROM orderitems WHERE itemId IN (SELECT itemId FROM menuitems WHERE businessId = ?)";
            jdbcTemplate.update(DELETE_ORDER_ITEMS_BY_ITEM_ID, id);

            final String DELETE_ORDER_ITEMS_BY_ORDER_ID = "DELETE FROM orderitems WHERE orderId IN (SELECT orderId FROM orders WHERE businessId = ?)";
            jdbcTemplate.update(DELETE_ORDER_ITEMS_BY_ORDER_ID, id);

            final String DELETE_MENU_ITEMS_BY_BUSINESS_ID = "DELETE FROM menuitems WHERE businessId = ?";
            jdbcTemplate.update(DELETE_MENU_ITEMS_BY_BUSINESS_ID, id);

            final String DELETE_ORDERS_BY_BUSINESS_ID = "DELETE FROM orders WHERE businessId = ?;";
            jdbcTemplate.update(DELETE_ORDERS_BY_BUSINESS_ID, id);

            final String DELETE_BUSINESS_BY_ID = "DELETE FROM business WHERE businessId = ?";
            jdbcTemplate.update(DELETE_BUSINESS_BY_ID, id);
        } catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error deleting business by ID", ex);
        }
    }
}
