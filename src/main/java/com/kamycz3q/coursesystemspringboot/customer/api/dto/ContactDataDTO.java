package com.kamycz3q.coursesystemspringboot.customer.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public record ContactDataDTO(
    @NotEmpty
    String phoneNumber,
    @Email
    String email
) {}
