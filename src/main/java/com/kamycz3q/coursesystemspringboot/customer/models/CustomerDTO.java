package com.kamycz3q.coursesystemspringboot.customer.models;

import com.kamycz3q.coursesystemspringboot.guest.companyData.CompanyData;
import com.kamycz3q.coursesystemspringboot.guest.personalData.PersonalData;

public record CustomerDTO(
        Long id,
        PersonalData personalData,
        CompanyData companyData
) {
}
