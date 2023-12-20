package com.Employee.employeeonboarding.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private String source = "EMPLOYEE_ONBOARDING";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime startTime = LocalDateTime.now();
    private String status = "SUCCESS";
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }
}
