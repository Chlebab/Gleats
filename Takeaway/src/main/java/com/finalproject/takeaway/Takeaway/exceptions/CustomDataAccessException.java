package com.finalproject.takeaway.Takeaway.exceptions;

import org.springframework.dao.DataAccessException;

public class CustomDataAccessException extends DataAccessException {

    /**
     * Constructor for CustomDataAccessException.
     * @param msg the detail message
     */
    public CustomDataAccessException(String msg) {
        super(msg);
    }

    /**
     * Constructor for CustomDataAccessException.
     * @param msg the detail message
     * @param cause the root cause (usually from using a underlying
     * data access API such as JDBC)
     */
    public CustomDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}