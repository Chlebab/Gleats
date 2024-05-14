package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dto.MenuItem;

import java.util.List;
/**
 * The MenuItemService interface provides methods to manage menu items.
 */
public interface MenuItemService {

    /**
     * Retrieves all menu items.
     *
     * @return a list of all menu items
     */
    List<MenuItem> getallMenuItems();

    /**
     * Retrieves all menu items by business ID.
     *
     * @param id the business ID
     * @return a list of menu items associated with the specified business ID
     */
    List<MenuItem> getAllMenuItemsByBusinessId(int id);

    /**
     * Adds a new menu item.
     *
     * @param menuItem the menu item to be added
     * @return the added menu item
     */
    MenuItem addNewMenuItem(MenuItem menuItem);

    /**
     * Updates the data of a menu item.
     *
     * @param id        the ID of the menu item to be updated
     * @param menuItem  the updated menu item data
     * @return the updated menu item
     */
    MenuItem updateMenuItemData(int id, MenuItem menuItem);

    /**
     * Deletes a menu item by ID.
     *
     * @param id the ID of the menu item to be deleted
     */
    void deleteMenuItemById(int id);

}
