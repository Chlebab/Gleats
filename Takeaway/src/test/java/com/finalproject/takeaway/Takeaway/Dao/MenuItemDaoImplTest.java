package com.finalproject.takeaway.Takeaway.Dao;

import com.finalproject.takeaway.Takeaway.dao.MenuItemDaoImpl;
import com.finalproject.takeaway.Takeaway.dto.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the MenuItemDaoImpl class.
 */
class MenuItemDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MenuItemDaoImpl menuItemDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for creating a new menu item.
     */
    @Test
    void testCreateNewMenuItem() {
        // Arrange
        MenuItem menuItem = new MenuItem();
        when(jdbcTemplate.update(anyString(), any(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenReturn(1);

        // Act
        MenuItem result = menuItemDao.createNewMenuItem(menuItem);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItemId());
    }

    /**
     * Test case for retrieving all menu items.
     */
    @Test
    void testGetAllMenuItems() {
        // Arrange
        List<MenuItem> expected = Arrays.asList(new MenuItem(), new MenuItem());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expected);

        // Act
        List<MenuItem> result = menuItemDao.getAllMenuItems();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for finding menu items by business ID.
     */
    @Test
    void testFindMenuItemByBusinessId() {
        // Arrange
        MenuItem expected = new MenuItem();
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), any())).thenReturn(Arrays.asList(expected));

        // Act
        List<MenuItem> result = menuItemDao.findMenuItemByBusinessId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expected, result.get(0));
    }

    /**
     * Test case for updating a menu item.
     */
    @Test
    void testUpdateMenuItem() {
        // Arrange
        MenuItem menuItem = new MenuItem();
        when(jdbcTemplate.update(anyString(), any(), any())).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> menuItemDao.updateMenuItem(menuItem));
    }

    /**
     * Test case for deleting a menu item.
     */
    @Test
    void testDeleteMenuItem() {
        // Arrange
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> menuItemDao.deleteMenuItem(1));
    }
}