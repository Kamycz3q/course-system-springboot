package com.kamycz3q.coursesystemspringboot.customer.personalData.models;

import com.kamycz3q.coursesystemspringboot.customer.companyData.CompanyData;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record PersonalDataDTO(
        @NonNull String name,
        @NonNull String surname,

        @NonNull String email,

        @NonNull String city,
        @NonNull String address,
        @NonNull String postcode,
        @Nullable CompanyData companyData
        ) {

}
