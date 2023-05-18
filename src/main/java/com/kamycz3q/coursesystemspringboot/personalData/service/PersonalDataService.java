package com.kamycz3q.coursesystemspringboot.personalData.service;

import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalData;
import com.kamycz3q.coursesystemspringboot.personalData.record.CreatePersonalRequest;
import com.kamycz3q.coursesystemspringboot.personalData.repo.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalDataService {
    private final PersonalDataRepository personalDataRepository;

    @Autowired
    public PersonalDataService(PersonalDataRepository personalData) {
        this.personalDataRepository = personalData;
    }

    private PersonalData setPersonalDataForObject(PersonalData personalData, CreatePersonalRequest req) {
        personalData.setName(req.name());
        personalData.setSurname(req.surname());
        personalData.setEmail(req.email());
        personalData.setBirthDate(new Timestamp(req.birthDateTimestamp()));
        personalData.setCity(req.city());
        personalData.setAddress(req.address());
        personalData.setPostCode(req.postcode());
        personalData.setPesel(req.pesel());
        return personalData;
    }
    public PersonalData createPersonalData(CreatePersonalRequest req) {
        PersonalData personalData = new PersonalData();
        setPersonalDataForObject(personalData, req);
        return personalDataRepository.save(personalData);
    }

    public PersonalData editPersonalData(long id, CreatePersonalRequest req) {
        Optional<PersonalData> personalDataOptional = personalDataRepository.findById(id);
        if (personalDataOptional.isEmpty()) {
            return null;
        }
        PersonalData personalData = setPersonalDataForObject(personalDataOptional.get(), req);

        return personalDataRepository.save(personalData);
    }

    public PersonalData getPersonalData(Long id) {
        Optional<PersonalData> personalDataOptional = personalDataRepository.findById(id);
        return personalDataOptional.orElse(null);
    }
    public List<PersonalData> getPersonalDataList() {
        return personalDataRepository.findAll();
    }

    public void deletePersonalData(Long id) {
        Optional<PersonalData> personalDataOptional = personalDataRepository.findById(id);
        if (personalDataOptional.isEmpty()) {
            return;
        }
        personalDataRepository.deleteById(id);
    }


}
