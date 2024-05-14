package com.finalproject.takeaway.Takeaway.service;

import com.finalproject.takeaway.Takeaway.dao.BusinessDao;
import com.finalproject.takeaway.Takeaway.dto.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the BusinessServiceImpl class.
 */
class BusinessServiceImplTest {
    @Mock
    private BusinessDao businessDao;

    @InjectMocks
    private BusinessServiceImpl businessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the getAllBusiness method.
     * It verifies that the getAllBusiness method returns the expected list of businesses.
     */
    @Test
    void testGetAllBusiness() {
        // Arrange
        List<Business> expected = Arrays.asList(new Business(), new Business());
        when(businessDao.getAllBusinesses()).thenReturn(expected);

        // Act
        List<Business> result = businessService.getAllBusiness();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for the getBusinessById method.
     * It verifies that the getBusinessById method returns the expected business object.
     */
    @Test
    void testGetBusinessById() {
        // Arrange
        Business expected = new Business();
        when(businessDao.findBusinessById(anyInt())).thenReturn(expected);

        // Act
        Business result = businessService.getBusinessById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for the addNewBusiness method.
     * It verifies that the addNewBusiness method returns the added business object.
     */
    @Test
    void testAddNewBusiness() {
        // Arrange
        Business business = new Business();
        business.setBusinessName("Test Business"); // Set a business name
        when(businessDao.createNewBusiness(any(Business.class))).thenReturn(business);

        // Act
        Business result = businessService.addNewBusiness(business);

        // Assert
        assertNotNull(result);
        assertEquals(business, result);
    }

    /**
     * Test case for the updateBusinessData method.
     * It verifies that the updateBusinessData method returns the updated business object.
     */
    @Test
    void testUpdateBusinessData() {
        // Arrange
        Business business = new Business();
        doNothing().when(businessDao).updateBusiness(any(Business.class));

        // Act
        Business result = businessService.updateBusinessData(1, business);


        // Assert
        assertNotNull(result);
        assertEquals(business, result);
    }

    /**
     * Test case for the deleteBusinessById method.
     * It verifies that the deleteBusinessById method does not throw any exception.
     */
    @Test
    void testDeleteBusinessById() {
        // Arrange
        // No return value to capture

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> businessService.deleteBusinessById(1));
    }
}