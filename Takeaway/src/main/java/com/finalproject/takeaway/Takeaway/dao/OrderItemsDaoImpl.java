package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dao.mappers.OrderItemsMapper;
import com.finalproject.takeaway.Takeaway.dto.OrderItems;
import com.finalproject.takeaway.Takeaway.exceptions.CustomDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemsDaoImpl implements OrderItemsDao {
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs a new OrderItemsDaoImpl with the specified JdbcTemplate.
     *
     * @param jdbcTemplate the JdbcTemplate to be used for database operations
     */
    public OrderItemsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new order items record in the database.
     *
     * @param orderItems the OrderItems object to be created
     * @return the created OrderItems object with the generated orderItemsId
     */
    @Override
    public OrderItems createNewOrderItems(OrderItems orderItems) throws CustomDataAccessException {
        try {
            final String INSERT_ORDERITEMS = "INSERT INTO orderitems (orderId, itemId) VALUES(?, ?)";
            jdbcTemplate.update(INSERT_ORDERITEMS, orderItems.getOrderId(), orderItems.getItemId());

            final String GET_NEWEST = "SELECT MAX(orderItemsId) FROM orderItems";
            orderItems.setOrderItemsId(jdbcTemplate.queryForObject(GET_NEWEST, new SingleColumnRowMapper<Integer>()));
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error creating new orderitems", ex);
        }
        return orderItems;
    }

    /**
     * Retrieves all order items from the database.
     *
     * @return a list of all OrderItems objects
     */
    @Override
    public List<OrderItems> getAllOrderItems() throws CustomDataAccessException {
        try {
            final String SELECT_ALL_ORDERITEMS = "SELECT * FROM orderitems";
            return jdbcTemplate.query(SELECT_ALL_ORDERITEMS, new OrderItemsMapper());
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error getting all order items", ex);
        }
    }

    /**
     * Finds an order items record in the database by its id.
     *
     * @param id the id of the order items record to be found
     * @return the found OrderItems object
     */
    @Override
    public OrderItems findOrderItemsById(int id) throws CustomDataAccessException {
        try {
            final String SELECT_ORDERITEMS_BY_ID = "SELECT * FROM orderitems WHERE orderItemsId = ?";
            return jdbcTemplate.queryForObject(SELECT_ORDERITEMS_BY_ID, new OrderItemsMapper(), id);
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error finding all order items by ID", ex);
        }
    }

    /**
     * Updates an existing order items record in the database.
     *
     * @param orderItems the updated OrderItems object
     */
    @Override
    public void updateOrderItems(OrderItems orderItems) throws CustomDataAccessException {
        try {
            final String UPDATE_ORDERITEMS = "UPDATE orderitems SET orderId = ?, itemId = ? WHERE orderId = ?";
            jdbcTemplate.update(UPDATE_ORDERITEMS,
                    orderItems.getOrderId(),
                    orderItems.getItemId());
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error updating order items", ex);
        }
    }

    /**
     * Deletes an order items record from the database by its id.
     *
     * @param id the id of the order items record to be deleted
     */
    @Override
    public void deleteOrderItems(int id) throws CustomDataAccessException {
        try {
            final String DELETE_ORDERITEMS_BY_ID = "DELETE FROM orderitems WHERE orderItemsId = ?";
            jdbcTemplate.update(DELETE_ORDERITEMS_BY_ID, id);
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error deleting order items by ID", ex);
        }
    }
}
