package com.kamycz3q.coursesystemspringboot.customer.personalData.models;


import com.kamycz3q.coursesystemspringboot.customer.companyData.models.CreateCompanyDataRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record CreatePersonalRequest(
        @NonNull String name,
        @NonNull  String surname,
        @NonNull String email,
        @NonNull Long birthDateTimestamp,
        @NonNull String city,
        @NonNull String address,
        @NonNull String postcode,
        @NonNull String pesel,
        @Nullable CreateCompanyDataRequest companyData

) {
}
