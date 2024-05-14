package com.finalproject.takeaway.Takeaway.controller;

import com.finalproject.takeaway.Takeaway.dto.Customer;
import com.finalproject.takeaway.Takeaway.dto.MenuItem;
import com.finalproject.takeaway.Takeaway.exceptions.MenuItemException;
import com.finalproject.takeaway.Takeaway.service.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/menuitems")
public class MenuItemController {
    @Autowired
    MenuItemServiceImpl menuItemServiceImpl;

    /**
     * Adds a new menu item.
     *
     * @param menuItem The menu item to be added.
     * @return The added menu item.
     */
    @PostMapping("/add")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        try {
            return menuItemServiceImpl.addNewMenuItem(menuItem);
        } catch (MenuItemException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    /**
     * Retrieves all menu items for a given business ID.
     *
     * @param businessId The ID of the business.
     * @return A list of menu items for the specified business ID.
     */
    @GetMapping("/getallitemsbybusinessid/{businessId}")
    public List<MenuItem> getAllMenuItems(@PathVariable int businessId) {
        try {
            return menuItemServiceImpl.getAllMenuItemsByBusinessId(businessId);
        } catch (MenuItemException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }


    /**
     * Updates an existing menu item.
     *
     * @param id the ID of the menu item to be updated
     * @param menuItem the updated menu item data
     * @return the updated menu item
     */

    @PutMapping("/update/{id}")
    public MenuItem editMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem){
        try {
            menuItem.setItemId(id);
            return menuItemServiceImpl.updateMenuItemData(id, menuItem);
        } catch (MenuItemException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }


    /**
     * Deletes a menu item by its ID.
     *
     * @param itemId The ID of the menu item to be deleted.
     */
    @DeleteMapping("/delete/{itemId}")
    public void deleteMenuItem(@PathVariable int itemId) {
        try {
            menuItemServiceImpl.deleteMenuItemById(itemId);
        } catch (MenuItemException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }
}
