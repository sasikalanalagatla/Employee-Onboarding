package com.Employee.employeeonboarding.exception;

import com.Employee.employeeonboarding.model.Employee;

public class MobileNumberAlreadyExistException extends RuntimeException {
    public MobileNumberAlreadyExistException(Employee employee) {
        super("Mobile number already exists for another employee");
    }
}
