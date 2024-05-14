package com.finalproject.takeaway.Takeaway.dao;

import com.finalproject.takeaway.Takeaway.dao.mappers.OrderMapper;
import com.finalproject.takeaway.Takeaway.dto.Order;
import com.finalproject.takeaway.Takeaway.exceptions.CustomDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs a new OrderDaoImpl with the specified JdbcTemplate.
     *
     * @param jdbcTemplate the JdbcTemplate to be used for database operations
     */
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new order in the database.
     *
     * @param order the Order object representing the new order
     * @return the Order object with the generated orderId
     */
    @Override
    public Order createNewOrder(Order order) throws CustomDataAccessException {
        try {
            final String INSERT_ORDER = "INSERT INTO orders (customerId, businessId, orderTime) VALUES(?, ?, ?)";
            jdbcTemplate.update(INSERT_ORDER, order.getCustomerId(), order.getBusinessId(), order.getOrderTime());

            final String GET_NEWEST = "SELECT MAX(orderId) FROM orders";
            order.setOrderId(jdbcTemplate.queryForObject(GET_NEWEST, new SingleColumnRowMapper<Integer>()));
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error creating new order", ex);
        }
        return order;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return a List of Order objects representing all orders
     */
    @Override
    public List<Order> getAllOrders() throws CustomDataAccessException {
        try {
            final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
            return jdbcTemplate.query(SELECT_ALL_ORDERS, new OrderMapper());
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error getting all orders", ex);
        }
    }

    /**
     * Finds an order in the database by its orderId.
     *
     * @param id the orderId of the order to be found
     * @return the Order object representing the found order
     */
    @Override
    public Order findOrderById(int id) throws CustomDataAccessException {
        try {
            final String SELECT_ORDERS_BY_ID = "SELECT * FROM orders WHERE orderId = ?";
            return jdbcTemplate.queryForObject(SELECT_ORDERS_BY_ID, new OrderMapper(), id);
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error finding all orders by ID", ex);
        }
    }

    /**
     * Updates an existing order in the database.
     *
     * @param order the Order object representing the updated order
     */
    @Override
    public void updateOrder(Order order) throws CustomDataAccessException {
        try {
            final String UPDATE_ORDER = "UPDATE orders SET customerId = ?, businessId = ?, orderTime = ? WHERE orderId = ?";
            jdbcTemplate.update(UPDATE_ORDER,
                    order.getCustomerId(),
                    order.getBusinessId(),
                    order.getOrderTime());
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error updating order", ex);
        }
    }

    /**
     * Deletes an order from the database by its orderId.
     *
     * @param id the orderId of the order to be deleted
     */
    @Override
    public void deleteOrder(int id) throws CustomDataAccessException {
        try {
            final String DELETE_ORDERS_BY_ID = "DELETE FROM orders WHERE orderId = ?";
            jdbcTemplate.update(DELETE_ORDERS_BY_ID, id);
        }   catch (CustomDataAccessException ex) {
            throw new CustomDataAccessException("Error deleting order by ID", ex);
        }
    }
}
