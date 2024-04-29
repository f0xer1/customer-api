package com.example.customerapi.repository;

import com.example.customerapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Repository interface for Customer entities.
 * Extends JpaRepository to inherit basic CRUD operations and additional query methods.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Retrieves a list of active customers.
     * @return A list of Customer entities with isActive=true.
     */
    List<Customer> findByIsActiveTrue();
    /**
     * Retrieves an active customer by their ID.
     * @param id The ID of the customer.
     * @return An Optional containing the Customer entity if found, or empty otherwise.
     */
    Optional<Customer> findByIdAndIsActiveTrue(Long id);
}
