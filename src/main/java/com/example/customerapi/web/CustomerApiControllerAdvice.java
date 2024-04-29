package com.example.customerapi.web;


import com.example.customerapi.web.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controller advice class for handling exceptions in the CustomerAPI application.
 * Provides centralized exception handling and custom error responses for different types of exceptions.
 */
@RestControllerAdvice
public class CustomerApiControllerAdvice {
    /**
     * Handles IllegalArgumentException and other RuntimeExceptions.
     * Returns a 400 Bad Request response with an ExceptionResponse object containing the exception message.
     *
     * @param exception The RuntimeException that was thrown.
     * @return A ResponseEntity with the ExceptionResponse object and HTTP status 400 Bad Request.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequest(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse(exception.getMessage()));
    }
    /**
     * Handles MethodArgumentNotValidException, which occurs when method argument validation fails.
     * Returns a 400 Bad Request response with an ExceptionResponse object containing a comma-separated list of validation error messages.
     *
     * @param exception The MethodArgumentNotValidException that was thrown.
     * @return A ResponseEntity with the ExceptionResponse object and HTTP status 400 Bad Request.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationError(
            MethodArgumentNotValidException exception) {

        var errorMessages = exception.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());


        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.join(", ", errorMessages),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))));
    }
    /**
     * Helper method to create an ExceptionResponse object with the given message and current time.
     *
     * @param message The exception message.
     * @return An ExceptionResponse object with the provided message and the current time formatted as "hh:mm:ss".
     */
    private ExceptionResponse exceptionResponse(String message) {
        return new ExceptionResponse(message,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    }
}
