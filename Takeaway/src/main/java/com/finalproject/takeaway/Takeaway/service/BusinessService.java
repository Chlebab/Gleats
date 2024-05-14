package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dto.Business;
import com.finalproject.takeaway.Takeaway.dto.Customer;

import java.util.List;

/**
 * The BusinessService interface provides methods to interact with business entities.
 */
public interface BusinessService {
    /**
     * Retrieves all businesses.
     *
     * @return a list of all businesses
     */
    List<Business> getAllBusiness();

    /**
     * Retrieves a business by its ID.
     *
     * @param id the ID of the business to retrieve
     * @return the business with the specified ID, or null if not found
     */
    Business getBusinessById(int id);

    /**
     * Adds a new business.
     *
     * @param business the business to add
     * @return the added business
     */
    Business addNewBusiness(Business business);

    /**
     * Updates the data of a business.
     *
     * @param id       the ID of the business to update
     * @param business the updated business data
     * @return the updated business
     */
    Business updateBusinessData(int id, Business business);

    /**
     * Deletes a business by its ID.
     *
     * @param id the ID of the business to delete
     */
    void deleteBusinessById(int id);
}
