package com.kamycz3q.coursesystemspringboot.course.certificate.api;

import com.kamycz3q.coursesystemspringboot.course.certificate.api.dto.request.CertificateCreateRequest;
import com.kamycz3q.coursesystemspringboot.course.certificate.logic.CertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/certificates")
public class CourseController {
    private final CertificateService certificateService;
    @PostMapping
    public ResponseEntity<?> createCertificate(@Valid @RequestBody CertificateCreateRequest req) {
        return certificateService.createCertificate(req);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCertificate(@PathVariable("id") Long id ) {
        return certificateService.getCertificate(id);
    }

    @PostMapping("/give/{certificateId}/{customerId}")
    public ResponseEntity<?> giveCertificate(@PathVariable("certificateId") Long cerificateId, @PathVariable("customerId") Long customerId) {
        return  certificateService.giveCertificate(cerificateId, customerId);
    }

    @DeleteMapping("/revoke/{certificateId}/{customerId}")
    public ResponseEntity<?> revokeCertificate(@PathVariable("certificateId") Long cerificateId, @PathVariable("customerId") Long customerId) {
        return  certificateService.revokeCertificate(cerificateId, customerId);
    }

    @DeleteMapping("{certificateId}")

    public ResponseEntity<?> removeCertificate(@PathVariable("certificateId") Long certificateId) {
        return  certificateService.deleteCertificate(certificateId);
    }
}
