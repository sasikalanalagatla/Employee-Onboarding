package com.Employee.employeeonboarding.controller;

import com.Employee.employeeonboarding.dto.ApiErrorResponse;
import com.Employee.employeeonboarding.dto.ApiSubError;
import com.Employee.employeeonboarding.dto.ApiResponse;
import com.Employee.employeeonboarding.dto.EmployeeDto;
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
    public List<EmployeeDto> getAllEmployees(){

        return employeeService.getAllEmployees();
    }

    @GetMapping("employee/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int employeeId) {
        EmployeeDto employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

    @PostMapping("/employee")
    public ResponseEntity<ApiResponse<EmployeeDto>> addEmployee(@RequestBody @Valid EmployeeDto employee){
        EmployeeDto res =  employeeService.addEmployee(employee);
        ApiResponse<EmployeeDto> apiResponse= new ApiResponse<>(res);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }
}
