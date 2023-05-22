package com.kamycz3q.coursesystemspringboot.customer.models;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record CreateCustomerRequest(
        @NonNull String login,
        @NonNull String password,
        @NonNull String personalNumber ,//pesel
        @Nullable CreateCustomerRequest createCustomerRequest
) {
}
