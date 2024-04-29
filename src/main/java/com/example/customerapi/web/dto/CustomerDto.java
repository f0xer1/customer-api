package com.example.customerapi.web.dto;

import lombok.Data;
/**
 * Data Transfer Object (DTO) for representing a customer.
 * Contains the customer's ID, email, full name, and phone number.
 */
@Data
public class CustomerDto {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
}
