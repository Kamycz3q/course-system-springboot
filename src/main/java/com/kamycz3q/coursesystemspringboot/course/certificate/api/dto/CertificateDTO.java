package com.kamycz3q.coursesystemspringboot.course.certificate.api.dto;

import com.kamycz3q.coursesystemspringboot.course.certificate.persistance.CertificateEntity;

import java.sql.Date;

public record CertificateDTO(
        Long id,
        Long courseId,
        String certificateName,
        Date date,
        String image
) {
    public static CertificateDTO fromCertificate(CertificateEntity certificateEntity) {
        return new CertificateDTO(
                certificateEntity.getId(),
                certificateEntity.getCourse().getId(),
                certificateEntity.getCertificateName(),
                (Date) certificateEntity.getDate(),
                certificateEntity.getImage()
        );
    }
}
