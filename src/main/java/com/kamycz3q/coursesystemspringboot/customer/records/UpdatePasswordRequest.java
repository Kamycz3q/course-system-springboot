package com.kamycz3q.coursesystemspringboot.customer.records;

public record UpdatePasswordRequest(
        String oldPassword,
        String newPassword,
        Long userId

) {
}
