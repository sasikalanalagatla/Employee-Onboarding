package com.Employee.employeeonboarding.dao;
import com.Employee.employeeonboarding.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {
    boolean existsByMobileNumber(long mobileNumber);
}
