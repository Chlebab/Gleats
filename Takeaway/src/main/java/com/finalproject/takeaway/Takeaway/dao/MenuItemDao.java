package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dto.MenuItem;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MenuItemDao {

    /**
     * Creates a new menu item.
     *
     * @param menuItem The menu item to be created.
     * @return The created menu item.
     */
    MenuItem createNewMenuItem(MenuItem menuItem) throws DataAccessException;

    /**
     * Retrieves all menu items.
     *
     * @return A list of all menu items.
     */
    List<MenuItem> getAllMenuItems() throws DataAccessException;

    /**
     * Finds menu items by business ID.
     *
     * @param id The ID of the business.
     * @return A list of menu items associated with the specified business ID.
     */
    List<MenuItem> findMenuItemByBusinessId(int id) throws DataAccessException;

    /**
     * Updates a menu item.
     *
     * @param menuItem The menu item to be updated.
     */
    void updateMenuItem(MenuItem menuItem) throws DataAccessException;

    /**
     * Deletes a menu item by ID.
     *
     * @param id The ID of the menu item to be deleted.
     */
    void deleteMenuItem(int id) throws DataAccessException;

}
