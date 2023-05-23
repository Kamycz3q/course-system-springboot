package com.kamycz3q.coursesystemspringboot.guest.personalData.models;


import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import com.kamycz3q.coursesystemspringboot.guest.companyData.models.CreateCompanyDataRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record CreatePersonalRequest(
        @NonNull String name,
        @NonNull  String surname,
        @NonNull Address address,
        @NonNull ContactData contactData,
        @NonNull String pesel,
        @Nullable CreateCompanyDataRequest companyData

) {
}
