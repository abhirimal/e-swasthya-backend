package com.star.eswasthyabackend.exception;

import com.star.eswasthyabackend.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleAppException(AppException appException) {
        ApiResponse response = new ApiResponse(false, appException.getMessage(), null);
        return ResponseEntity.status(appException.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Get the BindingResult from the exception
        BindingResult bindingResult = ex.getBindingResult();

        // Create a list to hold the error messages
        List<String> errorMessages = new ArrayList<>();

        // Iterate through the field errors and extract the error messages
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessages.add(fieldError.getDefaultMessage());
        }

        // Create a custom error response
        ApiResponse response = new ApiResponse(false, "Validation Error", errorMessages);

        // Return the custom response with a status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleException(Exception exception) {
//        CustomException customException = new CustomException("Server Error. Please try again later.", exception);
//        ApiResponse response = new ApiResponse(false, customException.getMessage(), null);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }

}
