package com.kamycz3q.coursesystemspringboot.customer.models;

import com.kamycz3q.coursesystemspringboot.guest.companyData.models.CompanyDataModel;
import com.kamycz3q.coursesystemspringboot.guest.personalData.models.PersonalDataModel;
import org.springframework.lang.NonNull;


public record CreateCustomerRequest(
        @NonNull PersonalDataModel personalData,
        @NonNull CompanyDataModel companyData
) {
}
