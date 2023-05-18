package com.kamycz3q.coursesystemspringboot.personalData.record;

import org.jetbrains.annotations.NotNull;

public record CreatePersonalCompanyRequest(
        @NotNull String name,
        @NotNull String surname,
        @NotNull String companyName,

        @NotNull String email,

        @NotNull String city,
        @NotNull String address,
        @NotNull String postcode,
        @NotNull Long nip
) {
}
