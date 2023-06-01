package com.kamycz3q.coursesystemspringboot.course.certificate.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;

@Validated
public record CertificateCreateRequest(
        @NotEmpty
        Long courseId,
        @NotEmpty
        String certificateName,
        @NotEmpty
        Date date,
        @URL
        String image
) {
}
