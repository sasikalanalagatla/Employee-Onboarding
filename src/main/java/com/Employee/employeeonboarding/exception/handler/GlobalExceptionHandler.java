package com.Employee.employeeonboarding.exception.handler;

import com.Employee.employeeonboarding.dto.ApiErrorResponse;
import com.Employee.employeeonboarding.dto.ApiSubError;
import com.Employee.employeeonboarding.exception.EmployeeNotFoundException;
import com.Employee.employeeonboarding.exception.MobileNumberAlreadyExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setStatus("400 NOT_FOUND");
//        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        if(ex.getBindingResult().hasErrors()) {
            List<ApiSubError> subErrors = ex.getBindingResult().getFieldErrors().stream()
                    .map(error -> new ApiSubError(
                            "employee",
                            error.getField(),
                            error.getRejectedValue(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
            errorResponse.setSubErrors(subErrors);
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setStatus("400 NOT_FOUND");
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MobileNumberAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiErrorResponse> handleMobileNumberAlreadyExist(MobileNumberAlreadyExistException ex){
        ApiErrorResponse errorResponse=new ApiErrorResponse();
        errorResponse.setStatus("409 CONFLICT");
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

}
