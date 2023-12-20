package com.Employee.employeeonboarding.exception;

 public class EmployeeNotFoundException extends RuntimeException {

        public EmployeeNotFoundException(Integer employeeId) {
            super("Employee was not found for parameters {id=" + employeeId + "}");
        }
}
