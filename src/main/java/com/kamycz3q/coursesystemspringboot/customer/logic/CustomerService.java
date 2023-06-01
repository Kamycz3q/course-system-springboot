package com.kamycz3q.coursesystemspringboot.customer.logic;

import com.kamycz3q.coursesystemspringboot.customer.api.dto.CompanyDataDTO;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.CustomerDTO;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.PersonalDataDTO;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntityMapper;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;
    public ResponseEntity<?> createCustomerFromData(PersonalDataDTO personalDataDTO, CompanyDataDTO companyDataDTO) {
        if (companyDataDTO.nip() == null && companyDataDTO.regon() == null) {
            return ResponseEntity.badRequest()
                    .body("NIP or REGON empty");
        }

        log.info("PersonalDataDTO: {}", personalDataDTO);

        CustomerEntity customerEntity = customerEntityMapper.toEntity(personalDataDTO, companyDataDTO);
        return ResponseEntity.ok()
                .body(CustomerDTO.fromCustomer(customerEntity));
    }

    public ResponseEntity<?> deleteCustomer(Long customerId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Customer not found");
        }
        customerRepository.delete(optionalCustomer.get());
        return ResponseEntity.ok()
                .body("Customer deleted");
    }

    public ResponseEntity<List<CustomerDTO>> listCustomers() {
        return ResponseEntity.ok()
                .body(customerRepository.findAll()
                .stream()
                .map(CustomerDTO::fromCustomer)
                .collect(toList()));
    }

    public ResponseEntity<?> getCustomer(Long customerId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Customer not found");
        }
        return ResponseEntity.ok()
                .body(optionalCustomer.get());
    }


}
