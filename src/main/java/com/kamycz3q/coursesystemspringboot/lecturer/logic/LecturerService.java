package com.kamycz3q.coursesystemspringboot.lecturer.logic;

import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerRepository;
import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerEntity;
import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;
    //tutaj nie powinienem korzystac
    private final CustomerRepository customerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository, CustomerRepository customerRepository) {
        this.lecturerRepository = lecturerRepository;
        this.customerRepository = customerRepository;
    }

    public LecturerEntity createLecturerFromUser(Long userId, String lecturerDisplayName) throws Exception {
        //spraWDZ CZY USER ISTNIEJE
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(userId);
        if (optionalCustomer.isEmpty()) {
            throw new Exception("customer dont exists!");
        }
        CustomerEntity customerEntity = optionalCustomer.get();

        //check is there no lecturer account
        LecturerEntity optionalLecturerEntity = lecturerRepository.getLecturerByCustomerId(userId);
        if (optionalLecturerEntity != null) {
            throw new Exception("customer already have lecturer account");
        }

        LecturerEntity lecturerEntity = new LecturerEntity();
        lecturerEntity.setCustomerId(userId);
        lecturerEntity.setDisplayName(lecturerDisplayName);
        return lecturerRepository.save(lecturerEntity);
    }

    public void changeLecturerDisplayName(Long lecturerId, String lecturerDisplayName) throws Exception {
        Optional<LecturerEntity> lecturerOptional = lecturerRepository.findById(lecturerId);
        if (lecturerOptional.isEmpty()) {
            throw new Exception("this lecturer dont exist");
        }

        LecturerEntity lecturerEntity = lecturerOptional.get();
        lecturerEntity.setDisplayName(lecturerDisplayName);
        lecturerRepository.save(lecturerEntity);
    }

    public void deleteLecturerAccount(Long lecturerId) throws Exception {
        Optional<LecturerEntity> lecturerOptional = lecturerRepository.findById(lecturerId);
        if (lecturerOptional.isEmpty()) {
            throw new Exception("this lecturer dont exist");
        }
        lecturerRepository.delete(lecturerOptional.get());
    }

    public LecturerEntity getLecturerAccount(Long lecturerId) throws Exception {
        Optional<LecturerEntity> lecturerOptional = lecturerRepository.findById(lecturerId);
        if (lecturerOptional.isEmpty()) {
            throw new Exception("this lecturer dont exist");
        }
        return lecturerOptional.get();
    }

    public List<LecturerEntity> lecturerList() {
        return lecturerRepository.findAll();
    }
}
