package com.kamycz3q.coursesystemspringboot.guest.companyData.models;

import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record CreateCompanyDataRequest(
        @NonNull Long personalDataId,
        @NonNull String companyName,
        @NonNull Address address,
        @NonNull ContactData contactData,
        @Nullable String nip,
        @Nullable String regon
) {
}
