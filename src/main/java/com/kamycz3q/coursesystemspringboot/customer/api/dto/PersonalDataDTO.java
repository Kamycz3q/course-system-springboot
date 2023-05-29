package com.kamycz3q.coursesystemspringboot.customer.api.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.validation.annotation.Validated;

@Validated
public record PersonalDataDTO(
        @NotEmpty
        String name,
        @NotEmpty
        String surname,
        @Valid
        AddressDTO address,
        @Valid
        ContactDataDTO contactData,
        @PESEL
        String pesel
){

}
