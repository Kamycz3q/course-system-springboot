package com.kamycz3q.coursesystemspringboot.customer.records;

import org.jetbrains.annotations.NotNull;

public record CreateCustomerRequest(
        @NotNull String login,
        @NotNull String password,
        @NotNull Long personalNumber //nip lub pesel
) {
}
