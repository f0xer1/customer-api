package com.example.customerapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Data Transfer Object (DTO) for updating an existing customer.
 * Contains validation constraints for the customer's full name and phone number.
 */
@Data

@AllArgsConstructor
public class CustomerUpdateDto {
    /**
     * The customer's updated full name.
     * Must not be blank and should be between 2 and 50 characters, including whitespaces.
     */
    @NotBlank(message = "Specify full name")
    @Size(min = 2, max = 50, message = "Full name should be between 2 and 50 characters, including whitespaces")
    private String fullName;
    /**
     * The customer's updated phone number.
     * Should be between 6 and 14 digits, starting with '+' followed by digits.
     */
    @Size(min = 6, max = 14, message = "Phone number should be between 6 and 14 digits")
    @Pattern(regexp = "^\\+[0-9]+$", message = "Phone number should start with '+', followed by digits")
    private String phone;
}
