package com.finalproject.takeaway.Takeaway.Dao;

import com.finalproject.takeaway.Takeaway.dao.BusinessDaoImpl;
import com.finalproject.takeaway.Takeaway.dto.Business;
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
 * This class contains unit tests for the BusinessDaoImpl class.
 */
class BusinessDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BusinessDaoImpl businessDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the createNewBusiness() method.
     * It verifies that a new business is created successfully.
     */
    @Test
    void testCreateNewBusiness() {
        // Arrange
        Business business = new Business();
        when(jdbcTemplate.update(anyString(), any(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class))).thenReturn(1);

        // Act
        Business result = businessDao.createNewBusiness(business);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getBusinessId());
    }

    /**
     * Test case for the getAllBusinesses() method.
     * It verifies that all businesses are retrieved successfully.
     */
    @Test
    void testGetAllBusinesses() {
        // Arrange
        List<Business> expected = Arrays.asList(new Business(), new Business());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expected);

        // Act
        List<Business> result = businessDao.getAllBusinesses();

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test case for the findBusinessById() method.
     * It verifies that a business is retrieved successfully by its ID.
     */
    @Test
    void testFindBusinessById() {
        // Arrange
        Business expected = new Business();
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), any())).thenReturn(expected);

        // Act
        Business result = businessDao.findBusinessById(1);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Test case for the updateBusiness() method.
     * It verifies that a business is updated successfully.
     */
    @Test
    void testUpdateBusiness() {
        // Arrange
        Business business = new Business();
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);

        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> businessDao.updateBusiness(business));
    }

    /**
     * Test case for the deleteBusiness() method.
     * It verifies that a business is deleted successfully.
     */
    @Test
    void testDeleteBusiness() {
        // Arrange
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        // Act
        // No return value to capture

        // Assert
        assertDoesNotThrow(() -> businessDao.deleteBusiness(1));
    }

}