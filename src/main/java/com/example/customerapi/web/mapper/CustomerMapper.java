package com.example.customerapi.web.mapper;

import com.example.customerapi.model.Customer;
import com.example.customerapi.web.dto.CustomerCreationDto;
import com.example.customerapi.web.dto.CustomerDto;
import com.example.customerapi.web.dto.CustomerUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
/**
 * Mapper interface for Customer entities and DTOs.
 * Defines methods for mapping between Customer, CustomerCreationDto, CustomerDto, and CustomerUpdateDto.
 * Uses MapStruct for efficient and type-safe mapping.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    /**
     * Maps a CustomerCreationDto to a Customer entity.
     * Ignores null values in the DTO.
     *
     * @param creationDto The CustomerCreationDto to map from.
     * @return The mapped Customer entity.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer toEntity(CustomerCreationDto creationDto);
    /**
     * Maps a Customer entity to a CustomerDto.
     *
     * @param customer The Customer entity to map from.
     * @return The mapped CustomerDto.
     */
    CustomerDto toPayload(Customer customer);
    /**
     * Updates a Customer entity with data from a CustomerUpdateDto.
     * Ignores null values in the DTO.
     *
     * @param updateDto The CustomerUpdateDto containing the updated data.
     * @param customer  The Customer entity to update.
     * @return The updated Customer entity.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer update(CustomerUpdateDto updateDto, @MappingTarget Customer customer);
}
