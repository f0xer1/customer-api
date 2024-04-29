package com.example.customerapi.web.controller;

import com.example.customerapi.service.CustomerService;
import com.example.customerapi.web.dto.CustomerCreationDto;
import com.example.customerapi.web.dto.CustomerDto;
import com.example.customerapi.web.dto.CustomerUpdateDto;
import com.example.customerapi.web.mapper.CustomerMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for Customer-related operations.
 * Handles HTTP requests for creating, retrieving, updating, and deleting customers.
 */
@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService service;
    private final CustomerMapper mapper;
    /**
     * Creates a new customer.
     * @param creationDto The DTO containing customer creation data.
     * @return The newly created customer DTO.
     */
    @PostMapping
    public ResponseEntity<CustomerDto> add(@RequestBody @Valid CustomerCreationDto creationDto) {
        var newCustomer = service.add(mapper.toEntity(creationDto));
        return new ResponseEntity<>(mapper.toPayload(newCustomer), HttpStatus.CREATED);
    }
    /**
     * Retrieves a list of active customers.
     * @return A list of active customer DTOs.
     */
    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(service.findByIsActiveTrue()
                .stream()
                .map(mapper::toPayload)
                .toList());
    }
    /**
     * Retrieves a customer by their ID.
     * @param id The ID of the customer.
     * @return The customer DTO if found, or 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById (@PathVariable Long id){
        return ResponseEntity.of(service.findByIdAndIsActiveTrue(id).map(mapper::toPayload));
    }
    /**
     * Updates an existing customer.
     * @param updateDto The DTO containing updated customer data.
     * @param id The ID of the customer to update.
     * @return The updated customer DTO if found, or 404 Not Found.
     */
    @PatchMapping ("/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody @Valid CustomerUpdateDto updateDto, @PathVariable Long id) {
        return ResponseEntity.of(service.findByIdAndIsActiveTrue(id)
                .map(customer -> mapper.update(updateDto, customer))
                .map(service::update)
                .map(mapper::toPayload));
    }
    /**
     * Deletes a customer (soft delete).
     * @param id The ID of the customer to delete.
     * @return 204 No Content on successful deletion, or 404 Not Found if the customer doesn't exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
