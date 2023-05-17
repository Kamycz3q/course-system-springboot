package com.kamycz3q.coursesystemspringboot.personalData.service;

import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalDataCompany;
import com.kamycz3q.coursesystemspringboot.personalData.record.CreatePersonalCompanyRequest;
import com.kamycz3q.coursesystemspringboot.personalData.repo.PersonalDataCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalDataCompanyService {
    private final PersonalDataCompanyRepository personalDataCompanyRepository;

    @Autowired
    public PersonalDataCompanyService(PersonalDataCompanyRepository personalDataCompanyRepository) {
        this.personalDataCompanyRepository = personalDataCompanyRepository;
    }
    private PersonalDataCompany setPersonalDataCompanyForObject(PersonalDataCompany personalData, CreatePersonalCompanyRequest req) {
        personalData.setName(req.name());
        personalData.setSurname(req.surname());
        personalData.setCompanyName(req.companyName());
        personalData.setEmail(req.email());
        personalData.setCity(req.city());
        personalData.setAddress(req.address());
        personalData.setPostCode(req.postcode());
        personalData.setNip(req.nip());
        return personalData;
    }
    public PersonalDataCompany createPersonalDataCompany(CreatePersonalCompanyRequest req) {
        PersonalDataCompany personalDataCompany = setPersonalDataCompanyForObject(new PersonalDataCompany(), req);
        return personalDataCompanyRepository.save(personalDataCompany);
    }

    public PersonalDataCompany editPersonalDataCompany(Long id, CreatePersonalCompanyRequest req) {
        Optional<PersonalDataCompany> optionalPersonalDataCompany = personalDataCompanyRepository.findById(id);
        if (optionalPersonalDataCompany.isEmpty()) {
            return null;
        }
        PersonalDataCompany personalDataCompany = setPersonalDataCompanyForObject(optionalPersonalDataCompany.get(), req);
        return personalDataCompanyRepository.save(personalDataCompany);
    }

    public void deletePersonalData(Long id) {
        Optional<PersonalDataCompany> optionalPersonalDataCompany = personalDataCompanyRepository.findById(id);
        if (optionalPersonalDataCompany.isEmpty()) {
            return;
        }
        personalDataCompanyRepository.deleteById(id);
    }

    public List<PersonalDataCompany> getPersonalDataCompanyList() {
        return personalDataCompanyRepository.findAll();
    }

    public PersonalDataCompany getPersonalDataCompany(Long id) {
        Optional<PersonalDataCompany> optionalPersonalDataCompany = personalDataCompanyRepository.findById(id);
        return optionalPersonalDataCompany.orElse(null);
    }

}
