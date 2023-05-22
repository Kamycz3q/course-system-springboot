package com.kamycz3q.coursesystemspringboot.customer.models;

public record UpdatePasswordRequest(
        String oldPassword,
        String newPassword,
        Long userId

) {
}
