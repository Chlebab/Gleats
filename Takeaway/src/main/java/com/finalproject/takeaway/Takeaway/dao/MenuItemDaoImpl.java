package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dao.mappers.MenuItemMapper;
import com.finalproject.takeaway.Takeaway.dto.MenuItem;
import com.finalproject.takeaway.Takeaway.exceptions.CustomDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MenuItemDaoImpl implements MenuItemDao{

    private final JdbcTemplate jdbcTemplate;

    public MenuItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new menu item in the database.
     * 
     * @param menuItem The menu item to be created.
     * @return The created menu item with the generated item ID.
     */
    @Override
    public MenuItem createNewMenuItem(MenuItem menuItem) throws CustomDataAccessException {
        try {
            final String INSERT_Menu = "INSERT INTO menuitems(itemName, businessId) VALUES(?, ?)";
            jdbcTemplate.update(INSERT_Menu, menuItem.getItemName(), menuItem.getBusinessId());

            final String GET_NEWEST = "SELECT MAX(itemId) FROM menuitems";
            menuItem.setItemId(jdbcTemplate.queryForObject(GET_NEWEST, new SingleColumnRowMapper<Integer>()));
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error creating new menu item", ex);
        }

        return menuItem;
    }

    /**
     * Retrieves all menu items from the database.
     * 
     * @return A list of all menu items.
     */
    @Override
    public List<MenuItem> getAllMenuItems() throws CustomDataAccessException {
        try {
            final String SELECT_ALL_BUSINESS = "SELECT * FROM menuitems";
            return jdbcTemplate.query(SELECT_ALL_BUSINESS, new MenuItemMapper());
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error getting all menu items", ex);
        }
    }

    /**
     * Retrieves menu items by business ID from the database.
     * 
     * @param id The ID of the business.
     * @return A list of menu items associated with the specified business ID.
     */
    @Override
    public List<MenuItem> findMenuItemByBusinessId(int id) throws CustomDataAccessException {
        try {
            final String SELECT_menuitems_BY_ID = "SELECT * FROM menuitems WHERE businessId = ?";
            return jdbcTemplate.query(SELECT_menuitems_BY_ID, new MenuItemMapper(), id);
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error finding all menu items by business ID", ex);
        }
    }

    /**
     * Updates a menu item in the database.
     * 
     * @param menuItem The updated menu item.
     */
    @Override
    public void updateMenuItem(MenuItem menuItem) throws CustomDataAccessException {
        try {
            final String UPDATE_MENUITEM = "UPDATE menuitems SET itemName = ?, businessId = ? WHERE itemId = ?";
            jdbcTemplate.update(UPDATE_MENUITEM,
                    menuItem.getItemName(),
                    menuItem.getBusinessId(),
                    menuItem.getItemId());
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error updating menu item", ex);
        }
    }

    /**
     * Deletes a menu item from the database.
     * 
     * @param id The ID of the menu item to be deleted.
     */
    @Override
    public void deleteMenuItem(int id) throws CustomDataAccessException {
        try {
            final String DELETE_ORDERITEMS_BY_ID = "DELETE FROM orderitems WHERE itemId = ?";
            jdbcTemplate.update(DELETE_ORDERITEMS_BY_ID, id);

            final String DELETE_MENUITEMS_BY_ID = "DELETE FROM menuitems WHERE itemId = ?";
            jdbcTemplate.update(DELETE_MENUITEMS_BY_ID, id);
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error deleting menu item by ID", ex);
        }
    }
}
