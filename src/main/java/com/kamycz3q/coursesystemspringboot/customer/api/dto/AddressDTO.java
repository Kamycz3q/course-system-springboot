package com.kamycz3q.coursesystemspringboot.customer.api.dto;


import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public record AddressDTO(
    @NotEmpty
    String street,
    @NotEmpty
    String buildingNo,
    @NotEmpty
    String flatNo,
    @NotEmpty
    String postalCode,
    @NotEmpty
    String city,
    @NotEmpty
    String country
){}
