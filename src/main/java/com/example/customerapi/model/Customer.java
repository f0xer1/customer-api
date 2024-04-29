package com.example.customerapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
/**
 * Entity class representing a customer.
 * Stores customer information such as full name, email, phone number, and active status.
 * The id field is the auto-generated primary key.
 * The created field stores the record creation time as Unix Epoch seconds.
 * The updated field stores the last update time for the record.
 */
@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "created")
    final private Long created = Instant.now().getEpochSecond();
    @Column(name = "updated")
    private Long updated;
    @Column(name = "email")
    private String email;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "is_active")
    private Boolean isActive = true;
}
