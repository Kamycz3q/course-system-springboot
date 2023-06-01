package com.kamycz3q.coursesystemspringboot.course.certificate.logic;

import com.kamycz3q.coursesystemspringboot.course.certificate.api.dto.request.CertificateCreateRequest;
import com.kamycz3q.coursesystemspringboot.course.certificate.api.dto.CertificateDTO;
import com.kamycz3q.coursesystemspringboot.course.certificate.persistance.CertificateEntity;
import com.kamycz3q.coursesystemspringboot.course.certificate.persistance.CertificateRepository;
import com.kamycz3q.coursesystemspringboot.course.persistence.CourseEntity;
import com.kamycz3q.coursesystemspringboot.course.persistence.CourseRepository;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final CourseRepository courseRepository;
    private final CustomerRepository customerRepository;
    public ResponseEntity<?> createCertificate(CertificateCreateRequest request) {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(request.courseId());
        if (!courseRepository.existsById(request.courseId())) {
            return ResponseEntity.badRequest()
                    .body("Course ID is not valid");
        }
        CertificateEntity certificateEntity = new CertificateEntity();
        certificateEntity.setCertificateName(request.certificateName());
        certificateEntity.setDate(request.date());
        certificateEntity.setImage(request.image());
        certificateEntity.setCourse(courseEntityOptional.get());
        certificateEntity.setCertificateName(request.certificateName());
        return ResponseEntity.ok()
                .body(CertificateDTO.fromCertificate(certificateRepository.save(certificateEntity)));
    }


    public ResponseEntity<?> getCertificate(Long id) {
        Optional<CertificateEntity> optionalCertificate = certificateRepository.findById(id);
        if (optionalCertificate.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Certificate ID is not valid");
        }
        return ResponseEntity.ok()
                .body(CertificateDTO.fromCertificate(optionalCertificate.get()));
    }

    public ResponseEntity<?> giveCertificate(Long certificateId, Long customerId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        Optional<CertificateEntity> optionalCertificate = certificateRepository.findById(certificateId);
        if (optionalCustomer.isEmpty() || optionalCertificate.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Wrong customer ID or Certificate ID");
        }
        CustomerEntity customerEntity = optionalCustomer.get();
        List<CertificateEntity> customerCertificates = customerEntity.getCertificates();
        CertificateEntity certificateEntity = optionalCertificate.get();
        customerCertificates.add(certificateEntity);
        customerEntity.setCertificates(customerCertificates);
        customerRepository.save(customerEntity);
        return ResponseEntity.ok().body("Added certificate " + certificateEntity.getCertificateName() + " for user");
    }

    public ResponseEntity<?> revokeCertificate(Long certificateId, Long customerId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        Optional<CertificateEntity> optionalCertificate = certificateRepository.findById(certificateId);
        if (optionalCustomer.isEmpty() || optionalCertificate.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Wrong customer ID or Certificate ID");
        }
        CustomerEntity customerEntity = optionalCustomer.get();
        List<CertificateEntity> customerCertificates = customerEntity.getCertificates();
        CertificateEntity certificateEntity = optionalCertificate.get();
        for (int i = 0; i < customerCertificates.size();i++) {
            CertificateEntity certificate = customerCertificates.get(i);
            if (certificate.equals(certificateEntity)) {
                customerCertificates.remove(i);
                break;
            }
        }
        customerEntity.setCertificates(customerCertificates);
        customerRepository.save(customerEntity);
        return ResponseEntity.ok()
                .body("Revoked certificate from user");
    }

    public ResponseEntity<?> deleteCertificate(Long id) {
        Optional<CertificateEntity> optionalCertificate = certificateRepository.findById(id);
        if (optionalCertificate.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Wrong customer ID or Certificate ID");
        }
        certificateRepository.deleteById(id);
        return  ResponseEntity.ok()
                .body("Deleted certficate");
    }
}
