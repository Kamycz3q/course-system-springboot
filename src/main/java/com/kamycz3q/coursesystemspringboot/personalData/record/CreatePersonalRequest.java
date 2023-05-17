package com.kamycz3q.coursesystemspringboot.personalData.record;

import org.jetbrains.annotations.NotNull;

public record CreatePersonalRequest(
        @NotNull String name,
        @NotNull  String surname,
        @NotNull String email,
        @NotNull Long birthDateTimestamp,
        @NotNull String city,
        @NotNull String address,
        @NotNull String postcode,
        @NotNull Integer pesel

) {
}
