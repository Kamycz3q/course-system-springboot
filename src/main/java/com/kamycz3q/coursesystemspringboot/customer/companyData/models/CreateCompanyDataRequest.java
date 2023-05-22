package com.kamycz3q.coursesystemspringboot.customer.companyData.models;

import org.springframework.lang.NonNull;

public record CreateCompanyDataRequest(
        @NonNull String companyName,

        @NonNull String email,

        @NonNull String city,
        @NonNull String address,
        @NonNull String postcode,
        @NonNull String nip
) {
}
