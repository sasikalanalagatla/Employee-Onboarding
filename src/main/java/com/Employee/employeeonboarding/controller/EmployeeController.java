package com.Employee.employeeonboarding.controller;

import com.Employee.employeeonboarding.dto.ApiErrorResponse;
import com.Employee.employeeonboarding.dto.ApiSubError;
import com.Employee.employeeonboarding.dto.ApiResponse;
import com.Employee.employeeonboarding.model.Employee;
import com.Employee.employeeonboarding.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @PostMapping("/employee")
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@RequestBody @Valid Employee employee){
        Employee res =  employeeService.addEmployee(employee);
        ApiResponse<Employee> apiResponse= new ApiResponse<>(res);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
            Employee employee = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
    }


//    @PostMapping("employee")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ApiSubError> subErrors = bindingResult.getFieldErrors().stream()
                    .map(error -> new ApiSubError(
                            "employee",
                            error.getField(),
                            error.getRejectedValue(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
            ApiErrorResponse errorResponse = new ApiErrorResponse(
                    "BAD_REQUEST",
                    "Validation errors",
                    LocalDateTime.now(),
                    subErrors);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
}
