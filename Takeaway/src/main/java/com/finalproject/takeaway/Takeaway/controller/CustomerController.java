package com.finalproject.takeaway.Takeaway.controller;

import com.finalproject.takeaway.Takeaway.dto.Customer;
import com.finalproject.takeaway.Takeaway.exceptions.CustomerException;
import com.finalproject.takeaway.Takeaway.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        try {
            return customerServiceImpl.getAllCustomers();
        } catch (CustomerException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer
     * @return the customer with the specified ID
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
        try {
            return customerServiceImpl.getCustomerById(id);
        } catch (CustomerException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    /**
     * Adds a new customer.
     *
     * @param customer the customer to be added
     * @return the added customer
     */
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        try {
            return customerServiceImpl.addNewCustomer(customer);
        } catch (CustomerException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    /**
     * Updates an existing customer.
     *
     * @param id the ID of the customer to be updated
     * @param customer the updated customer data
     * @return the updated customer
     */
    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        try {
            customer.setCustomerId(id);
            return customerServiceImpl.updateCustomerData(id, customer);
        } catch (CustomerException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to be deleted
     */
    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable int id){
        try {
            customerServiceImpl.deleteCustomerById(id);
        } catch (CustomerException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}