package com.Employee.employeeonboarding.exception;

import com.Employee.employeeonboarding.model.Employee;

public class MobileNumberAlreadyExistException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MobileNumberAlreadyExistException(String message) {
        super(message);
    }
}
