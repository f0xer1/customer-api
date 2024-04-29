package com.example.customerapi.service.impl;

import com.example.customerapi.exception.CustomerAlreadyExistsException;
import com.example.customerapi.model.Customer;
import com.example.customerapi.repository.CustomerRepository;
import com.example.customerapi.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for Customer-related operations.
 * Handles customer creation, retrieval, updating, and deletion.
 */
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    /**
     * Adds a new customer to the repository.
     *
     * @param customer The customer object to add.
     * @return The saved customer object.
     */

    @Override
    @Transactional
    public Customer add(Customer customer) {
        if (isFullNameInUse(customer)) {
            throw new CustomerAlreadyExistsException(
                    "Full name %s is already in use".formatted(customer.getFullName()));
        }
        if (isEmailInUse(customer)) {
            throw new CustomerAlreadyExistsException(
                    "Email %s is already in use".formatted(customer.getEmail()));
        }
        if (isPhoneInUse(customer)) {
            throw new CustomerAlreadyExistsException(
                    "Phone number %s is already in use".formatted(customer.getEmail()));
        }
        return repository.save(customer);
    }

    /**
     * Retrieves a list of active customers.
     *
     * @return A list of active Customer entities.
     */
    @Override
    public List<Customer> findByIsActiveTrue() {
        return repository.findByIsActiveTrue();
    }

    /**
     * Retrieves an active customer by their ID.
     *
     * @param id The ID of the customer.
     * @return An Optional containing the Customer entity if found, or empty otherwise.
     */
    @Override
    public Optional<Customer> findByIdAndIsActiveTrue(Long id) {
        return repository.findByIdAndIsActiveTrue(id);
    }

    /**
     * Updates an existing customer.
     * Sets the updated timestamp to the current time.
     *
     * @param customer The customer object to update.
     * @return The updated customer object.
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Customer update(Customer customer) {
        if (isFullNameInUse(customer)) {
            throw new CustomerAlreadyExistsException(
                    "Full name %s is already in use".formatted(customer.getFullName()));
        }
        if (isEmailInUse(customer)) {
            throw new CustomerAlreadyExistsException(
                    "Email %s is already in use".formatted(customer.getEmail()));
        }
        if (isPhoneInUse(customer)) {
            throw new CustomerAlreadyExistsException(
                    "Phone number %s is already in use".formatted(customer.getEmail()));
        }
        customer.setUpdated(Instant.now().getEpochSecond());
        return repository.save(customer);
    }

    /**
     * Checks if a customer exists with the given ID.
     *
     * @param id The ID of the customer.
     * @return True if a customer with the given ID exists, false otherwise.
     */
    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    /**
     * Soft-deletes a customer by setting their isActive flag to false.
     *
     * @param id The ID of the customer to delete.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        findByIdAndIsActiveTrue(id).filter(Customer::getIsActive).ifPresent(customer -> customer.setIsActive(false));
    }
    /**
     * Checks if the given full name is already in use by another customer.
     * Excludes the customer being updated (if any).
     *
     * @param customer The customer whose full name is being checked.
     * @return true if the full name is in use by another customer, false otherwise.
     */
    private boolean isFullNameInUse(Customer customer) {
        return repository.findByFullName(customer.getFullName())
                .filter(found -> !found.getId().equals(customer.getId())).isPresent();
    }
    /**
     * Checks if the given email address is already in use by another customer.
     * Excludes the customer being updated (if any).
     *
     * @param customer The customer whose email is being checked.
     * @return true if the email is in use by another customer, false otherwise.
     */
    private boolean isEmailInUse(Customer customer) {
        return repository.findByEmail(customer.getEmail())
                .filter(found -> !found.getId().equals(customer.getId())).isPresent();
    }  /**
     * Checks if the given phone number is already in use by another customer.
     * Excludes the customer being updated (if any).
     *
     * @param customer The customer whose email is being checked.
     * @return true if the phone is in use by another customer, false otherwise.
     */
    private boolean isPhoneInUse(Customer customer) {
        return repository.findByPhone(customer.getPhone())
                .filter(found -> !found.getId().equals(customer.getId())).isPresent();
    }

}
