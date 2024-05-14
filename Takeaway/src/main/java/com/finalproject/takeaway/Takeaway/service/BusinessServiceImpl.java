package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.BusinessDao;
import com.finalproject.takeaway.Takeaway.dto.Business;
import com.finalproject.takeaway.Takeaway.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the BusinessService interface and provides the implementation for various business-related operations.
 */
@Service
public class BusinessServiceImpl implements BusinessService{

    @Autowired
    BusinessDao businessDao;

    /**
     * Constructs a new BusinessServiceImpl object with the specified BusinessDao.
     * @param businessDao The BusinessDao object to be used for data access.
     */
    public BusinessServiceImpl(BusinessDao businessDao) {
        this.businessDao = businessDao;
    }

    /**
     * Retrieves a list of all businesses.
     * @return A list of Business objects representing all the businesses.
     */
    @Override
    public List<Business> getAllBusiness() {
        try {
            return businessDao.getAllBusinesses();
        } catch (DataAccessException ex) {
            throw new BusinessException("Error retrieving all businesses", ex);
        }
    }

    /**
     * Retrieves a business by its ID.
     * @param id The ID of the business to retrieve.
     * @return The Business object with the specified ID, or a Business object with a default name if the business is not found.
     */
    @Override
    public Business getBusinessById(int id) {
        try {
            Business business = businessDao.findBusinessById(id);
            if (business == null) {
                throw new BusinessException("Business with id " + id + " not found");
            }
            return business;
        } catch (DataAccessException ex) {
            throw new BusinessException("Error retrieving business with id " + id, ex);
        }
    }

    /**
     * Adds a new business.
     * @param business The Business object representing the new business to be added.
     * @return The added Business object, or a Business object with a default name if the business name is blank.
     */
    @Override
    public Business addNewBusiness(Business business) {
        if (business.getBusinessName().isBlank()){
            throw new BusinessException("Business Name blank, business NOT added");
        } else {
            try {
                businessDao.createNewBusiness(business);
            } catch (DataAccessException ex) {
                throw new BusinessException("Error adding new business", ex);
            }
        }
        return business;
    }

    /**
     * Updates the data of an existing business.
     * @param id The ID of the business to be updated.
     * @param business The Business object containing the updated data.
     * @return The updated Business object, or a Business object with a default name if the IDs do not match.
     */
    @Override
    public Business updateBusinessData(int id, Business business) {
        try {
            businessDao.updateBusiness(business);
        } catch (DataAccessException ex) {
            throw new BusinessException("Error updating business with id " + id, ex);
        }

        return business;
    }

    /**
     * Deletes a business by its ID.
     * @param id The ID of the business to be deleted.
     */
    @Override
    public void deleteBusinessById(int id) {
        try {
            businessDao.deleteBusiness(id);
        } catch (DataAccessException ex) {
            throw new BusinessException("Error deleting business with id " + id, ex);
        }
    }
}
