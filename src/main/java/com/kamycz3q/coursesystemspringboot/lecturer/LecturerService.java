package com.kamycz3q.coursesystemspringboot.lecturer;

import com.kamycz3q.coursesystemspringboot.customer.Customer;
import com.kamycz3q.coursesystemspringboot.customer.CustomerRepository;
import com.kamycz3q.coursesystemspringboot.customer.enums.AccountPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository, CustomerRepository customerRepository) {
        this.lecturerRepository = lecturerRepository;
        this.customerRepository = customerRepository;
    }

    public Lecturer createLecturerFromUser(Long userId, String lecturerDisplayName) throws Exception {
        //spraWDZ CZY USER ISTNIEJE
        Optional<Customer> optionalCustomer = customerRepository.findById(userId);
        if (optionalCustomer.isEmpty()) {
            throw new Exception("customer dont exists!");
        }
        Customer customer = optionalCustomer.get();
        //check permissions
        if (customer.getAccountPermissions() != null) {
            if (!customer.getAccountPermissions().contains(AccountPermissions.LECTURER)) {
                throw new Exception("customer dont have permissions for creating lecturer!");

            }
        } else {
            throw new Exception("customer dont have permissions for creating lecturer!");
        }

        //check is there no lecturer account
        Lecturer optionalLecturer = lecturerRepository.getLecturerByCustomerId(userId);
        if (optionalLecturer != null) {
            throw new Exception("customer already have lecturer account");
        }

        Lecturer lecturer = new Lecturer();
        lecturer.setCustomerId(userId);
        lecturer.setDisplayName(lecturerDisplayName);
        return lecturerRepository.save(lecturer);
    }

    public void changeLecturerDisplayName(Long lecturerId, String lecturerDisplayName) throws Exception {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(lecturerId);
        if (lecturerOptional.isEmpty()) {
            throw new Exception("this lecturer dont exist");
        }

        Lecturer lecturer = lecturerOptional.get();
        lecturer.setDisplayName(lecturerDisplayName);
        lecturerRepository.save(lecturer);
    }

    public void deleteLecturerAccount(Long lecturerId) throws Exception {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(lecturerId);
        if (lecturerOptional.isEmpty()) {
            throw new Exception("this lecturer dont exist");
        }
        lecturerRepository.delete(lecturerOptional.get());
    }

    public Lecturer getLecturerAccount(Long lecturerId) throws Exception {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(lecturerId);
        if (lecturerOptional.isEmpty()) {
            throw new Exception("this lecturer dont exist");
        }
        return lecturerOptional.get();
    }

    public List<Lecturer> lecturerList() {
        return lecturerRepository.findAll();
    }
}
