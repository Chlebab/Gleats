package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dto.Business;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BusinessDao {
    
    /**
     * Creates a new business.
     *
     * @param business the business object to be created
     * @return the created business object
     */
    Business createNewBusiness(Business business) throws DataAccessException;
    
    /**
     * Retrieves a list of all businesses.
     *
     * @return a list of all businesses
     */
    List<Business> getAllBusinesses() throws DataAccessException;
    
    /**
     * Finds a business by its ID.
     *
     * @param id the ID of the business to find
     * @return the found business object, or null if not found
     */
    Business findBusinessById(int id) throws DataAccessException;
    
    /**
     * Updates a business.
     *
     * @param business the business object to be updated
     */
    void updateBusiness(Business business) throws DataAccessException;
    
    /**
     * Deletes a business by its ID.
     *
     * @param id the ID of the business to delete
     */
    void deleteBusiness(int id) throws DataAccessException;
}
