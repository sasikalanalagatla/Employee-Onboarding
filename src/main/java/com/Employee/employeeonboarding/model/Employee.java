package com.Employee.employeeonboarding.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String empName;
    private int age;
    private String designation;
    private long mobileNumber;
    private String gmail;
    public Employee(String empName, int age, String designation, long mobileNumber, String gmail) {
        this.empName = empName;
        this.age = age;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.gmail = gmail;
    }
}
