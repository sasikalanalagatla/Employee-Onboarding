package com.Employee.employeeonboarding.service;
import com.Employee.employeeonboarding.dto.EmployeeDto;
import com.Employee.employeeonboarding.exception.EmployeeNotFoundException;
import com.Employee.employeeonboarding.exception.MobileNumberAlreadyExistException;
import com.Employee.employeeonboarding.dao.EmployeeDao;
import com.Employee.employeeonboarding.model.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDao employeeDao;
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        List<EmployeeDto> response = new ArrayList<>();
        for (int i=0;i<employees.size();i++) {
            Employee e= employees.get(i);
            EmployeeDto employeeDto = new EmployeeDto(
                    e.getEmployeeId(), e.getEmpName(), e.getAge(), e.getDesignation(),
                    e.getMobileNumber(), e.getGmail()
            );
            response.add(employeeDto);
        }
        return response;
    }

    public EmployeeDto getEmployeeById(Integer employeeId) {
         Employee employee=employeeDao.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException(employeeId));
         EmployeeDto employeeDto=new EmployeeDto(employee.getEmployeeId(),employee.getEmpName(),employee.getAge(),
                 employee.getDesignation(),employee.getMobileNumber(),employee.getGmail());
         return employeeDto;
    }

    public String deleteEmployee(Integer employeeId) {
        employeeDao.deleteById(employeeId);
        return "success";
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        if(employeeDao.existsByMobileNumber(employeeDto.getMobileNumber())){
            throw new MobileNumberAlreadyExistException("Mobile number already exists for another employee");
        }
        Employee employee = new Employee(
                employeeDto.getEmpName(),employeeDto.getAge(),employeeDto.getDesignation(),employeeDto.getMobileNumber(),employeeDto.getGmail()
        );
        Employee e = employeeDao.save(employee);
        EmployeeDto res = new EmployeeDto(e.getEmployeeId(),e.getEmpName(),e.getAge(),
                e.getDesignation(),e.getMobileNumber(),e.getGmail());
        return res;
    }

}





















