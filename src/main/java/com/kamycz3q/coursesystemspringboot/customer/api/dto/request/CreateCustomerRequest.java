package com.kamycz3q.coursesystemspringboot.customer.api.dto.request;


import com.kamycz3q.coursesystemspringboot.customer.api.dto.CompanyDataDTO;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.PersonalDataDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public record CreateCustomerRequest(
        @Valid
        PersonalDataDTO personalData,
        @Valid
        CompanyDataDTO companyData

) {
}
