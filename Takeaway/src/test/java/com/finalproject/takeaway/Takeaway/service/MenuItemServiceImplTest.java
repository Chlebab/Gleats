package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.MenuItemDao;
import com.finalproject.takeaway.Takeaway.dto.MenuItem;
import com.finalproject.takeaway.Takeaway.exceptions.MenuItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the MenuItemServiceImpl class.
 */
class MenuItemServiceImplTest {

    @Mock
    private MenuItemDao menuItemDao;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the getallMenuItems() method.
     */
    @Test
    void testGetallMenuItems() {
        // Arrange
        List<MenuItem> expected = Arrays.asList(new MenuItem(), new MenuItem());
        when(menuItemDao.getAllMenuItems()).thenReturn(expected);

        // Act
        List<MenuItem> result = menuItemService.getallMenuItems();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for the getAllMenuItemsByBusinessId() method.
     */
    @Test
    void testGetAllMenuItemsByBusinessId() {
        // Arrange
        int id = 1;
        List<MenuItem> expected = Arrays.asList(new MenuItem(), new MenuItem());
        when(menuItemDao.findMenuItemByBusinessId(id)).thenReturn(expected);

        // Act
        List<MenuItem> result = menuItemService.getAllMenuItemsByBusinessId(id);

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for the getAllMenuItemsByBusinessId() method when an exception is thrown.
     */
    @Test
    void testGetAllMenuItemsByBusinessId_Exception() {
        // Arrange
        int id = 1;
        when(menuItemDao.findMenuItemByBusinessId(id)).thenThrow(new RuntimeException());

        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            menuItemService.getAllMenuItemsByBusinessId(id);
        });

        assertNotNull(exception);
    }

    /**
     * Test case for the addNewMenuItem() method.
     */
    @Test
    void testAddNewMenuItem() {
        // Arrange
        MenuItem menuItem = new MenuItem();
        menuItem.setItemName("Test Item");
        menuItem.setBusinessId(1);
        when(menuItemDao.createNewMenuItem(menuItem)).thenReturn(menuItem);

        // Act
        MenuItem result = menuItemService.addNewMenuItem(menuItem);

        // Assert
        assertNotNull(result);
        assertEquals("Test Item", result.getItemName());
        assertEquals(1, result.getBusinessId());
    }

    /**
     * Test case for the addNewMenuItem() method when the item name is missing.
     */
    @Test
    void testAddNewMenuItem_MissingItemName() {
        // Arrange
        MenuItem menuItem = new MenuItem();
        when(menuItemDao.createNewMenuItem(menuItem)).thenReturn(menuItem);

        // Act
        Throwable exception = assertThrows(MenuItemException.class, () -> {
            MenuItem result = menuItemService.addNewMenuItem(menuItem);
        });

        // Assert
        assertEquals("Missing item name", exception.getMessage());
    }

    /**
     * Test case for the addNewMenuItem() method when the business ID is incorrect.
     */
    @Test
    void testAddNewMenuItem_IncorrectBusinessId() {
        // Arrange
        MenuItem menuItem = new MenuItem();
        menuItem.setItemName("Test Item");
        menuItem.setBusinessId(0);
        when(menuItemDao.createNewMenuItem(menuItem)).thenReturn(menuItem);

        // Act
        Throwable exception = assertThrows(MenuItemException.class, () -> {
            MenuItem result = menuItemService.addNewMenuItem(menuItem);
        });

        // Assert
        assertEquals("Incorrect Business ID, menu item NOT added", exception.getMessage());
    }

    /**
     * Test case for the updateMenuItemData() method.
     */
    @Test
    void testUpdateMenuItemData() {
        // Arrange
        int id = 1;
        MenuItem menuItem = new MenuItem();
        menuItem.setItemId(1);
        doNothing().when(menuItemDao).updateMenuItem(any(MenuItem.class));

        // Act
        MenuItem result = menuItemService.updateMenuItemData(id, menuItem);

        // Assert
        assertNotNull(result);
        assertEquals(menuItem, result);
    }

    /**
     * Test case for the updateMenuItemData() method when the IDs do not match.
     */
    @Test
    void testUpdateMenuItemData_IDsDoNotMatch() {
        // Arrange
        int id = 1;
        MenuItem menuItem = new MenuItem();
        menuItem.setItemId(2);

        // Act
        Throwable exception = assertThrows(MenuItemException.class, () -> {
            MenuItem result = menuItemService.updateMenuItemData(id, menuItem);
        });

        // Assert
        assertEquals("IDs do not match, menu item not updated", exception.getMessage());
    }

    /**
     * Test case for the deleteMenuItemById() method.
     */
    @Test
    void testDeleteMenuItemById() {
        // Arrange
        int id = 1;

        // Act
        menuItemService.deleteMenuItemById(id);

        // Assert
        verify(menuItemDao, times(1)).deleteMenuItem(id);
    }

}