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
    @NotNull(message="User should not be null")
    private String empName;
    @Min(18)
    @Max(50)
    private int age;
    private String designation;
    @NotNull
    private long mobileNumber;
    @Email(message = "invalid email address")
    private String gmail;

}
