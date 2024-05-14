package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.MenuItemDao;
import com.finalproject.takeaway.Takeaway.dto.MenuItem;
import com.finalproject.takeaway.Takeaway.exceptions.MenuItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the MenuItemService interface and provides the implementation for various menu item operations.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService{

    @Autowired
    private final MenuItemDao menuItemDao;

    /**
     * Constructs a new MenuItemServiceImpl with the specified MenuItemDao.
     * @param menuItemDao The MenuItemDao to be used for data access.
     */
    public MenuItemServiceImpl(MenuItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    /**
     * Retrieves all menu items.
     * @return A list of all menu items.
     */
    @Override
    public List<MenuItem> getallMenuItems() {
        try {
            return menuItemDao.getAllMenuItems();
        } catch (Exception ex) {
            throw new MenuItemException("Error retrieving all menu items", ex);
        }
    }

    /**
     * Retrieves all menu items for a given business ID.
     * @param id The business ID.
     * @return A list of menu items for the specified business ID.
     */
    @Override
    public List<MenuItem> getAllMenuItemsByBusinessId(int id) {
        try{
            List<MenuItem> menuItems = menuItemDao.findMenuItemByBusinessId(id);
            if (menuItems == null || menuItems.isEmpty()) {
                throw new MenuItemException("No menu items found for business id " + id);
            }
            return menuItems;
        } catch (Exception e) {
            throw new MenuItemException("Error retrieving menu items for business id " + id, e);
        }
    }

    /**
     * Adds a new menu item.
     * @param menuItem The menu item to be added.
     * @return The added menu item.
     */
    @Override
    public MenuItem addNewMenuItem(MenuItem menuItem) {
        if (menuItem.getItemName() == null) {
            throw new MenuItemException("Missing item name");
        }
        else if ((int) menuItem.getBusinessId() == 0){
            throw new MenuItemException("Incorrect Business ID, menu item NOT added");
        }
        try {
            return menuItemDao.createNewMenuItem(menuItem);
        } catch (Exception ex) {
            throw new MenuItemException("Error adding new menu item", ex);
        }
    }

    /**
     * Updates the data of a menu item.
     * @param id The ID of the menu item to be updated.
     * @param menuItem The updated menu item data.
     * @return The updated menu item.
     */
    @Override
    public MenuItem updateMenuItemData(int id, MenuItem menuItem) {
        if (id != menuItem.getItemId()) {
            throw new MenuItemException("IDs do not match, menu item not updated");
        }
        try {
            menuItemDao.updateMenuItem(menuItem);
        } catch (Exception ex) {
            throw new MenuItemException("Error updating menu item with id " + id, ex);
        }
        return menuItem;
    }

    /**
     * Deletes a menu item by its ID.
     * @param id The ID of the menu item to be deleted.
     */
    @Override
    public void deleteMenuItemById(int id) {
        try {
            menuItemDao.deleteMenuItem(id);
        } catch (Exception ex) {
            throw new MenuItemException("Error deleting menu item with id " + id, ex);
        }
    }
}
