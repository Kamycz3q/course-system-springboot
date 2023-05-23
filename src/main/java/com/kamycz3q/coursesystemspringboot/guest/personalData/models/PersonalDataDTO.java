package com.kamycz3q.coursesystemspringboot.guest.personalData.models;

import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import com.kamycz3q.coursesystemspringboot.guest.companyData.CompanyData;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record PersonalDataDTO(
        @NonNull String name,
        @NonNull String surname,
        @NonNull Address address,
        @NonNull ContactData contactData,
        @Nullable CompanyData companyData
        ) {

}
