package com.example.customerapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Data Transfer Object (DTO) for representing an exception response.
 * Contains the exception message and the timestamp of the response.
 */
@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private String timeStamp;
}