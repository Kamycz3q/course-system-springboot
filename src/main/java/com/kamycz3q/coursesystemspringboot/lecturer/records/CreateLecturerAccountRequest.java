package com.kamycz3q.coursesystemspringboot.lecturer.records;

import org.jetbrains.annotations.NotNull;

public record CreateLecturerAccountRequest(
        @NotNull Long customerId,
        @NotNull String displayName
) {
}
