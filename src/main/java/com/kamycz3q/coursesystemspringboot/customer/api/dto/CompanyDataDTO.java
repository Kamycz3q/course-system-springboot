package com.kamycz3q.coursesystemspringboot.customer.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.validation.annotation.Validated;

@Validated
public record CompanyDataDTO(
        @NotEmpty
        String companyName,
        @Valid
        AddressDTO address,
        @Valid
        ContactDataDTO contactData,
        @NIP
        String nip,
        @REGON
        String regon
){

}
