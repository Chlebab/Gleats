package com.finalproject.takeaway.Takeaway.controller;

import com.finalproject.takeaway.Takeaway.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finalproject.takeaway.Takeaway.dto.Business;
import com.finalproject.takeaway.Takeaway.service.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The BusinessController class handles the HTTP requests related to the Business entity.
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessServiceImpl businessServiceImpl;

    /**
     * Adds a new business.
     *
     * @param business The business object to be added.
     * @return The added business object.
     */
    @PostMapping("/add")
    public Business addBusiness(@RequestBody Business business) {
        try {
            return businessServiceImpl.addNewBusiness(business);
        } catch (BusinessException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    /**
     * Retrieves all businesses.
     *
     * @return A list of all businesses.
     */
    @GetMapping("/getAll")
    public List<Business> getAllBusinesses() {
        try {
            return businessServiceImpl.getAllBusiness();
        } catch (BusinessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    /**
     * Retrieves a business by its ID.
     *
     * @param id The ID of the business to retrieve.
     * @return The business object with the specified ID.
     */
    @GetMapping("/get/{id}")
    public Business getBusinessById(@PathVariable int id) {
        try {
            return businessServiceImpl.getBusinessById(id);
        } catch (BusinessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    /**
     * Deletes a business by its ID.
     *
     * @param id The ID of the business to delete.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteBusinessById(@PathVariable int id) {
        try {
            businessServiceImpl.deleteBusinessById(id);
        } catch (BusinessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    /**
     * Updates a business by its ID.
     *
     * @param id       The ID of the business to update.
     * @param business The updated business object.
     * @return The updated business object.
     */
    @PutMapping("/update/{id}")
    public Business updateBusinessById(@PathVariable int id, @RequestBody Business business) {
        try {
            return businessServiceImpl.updateBusinessData(id, business);
        } catch (BusinessException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }
}
