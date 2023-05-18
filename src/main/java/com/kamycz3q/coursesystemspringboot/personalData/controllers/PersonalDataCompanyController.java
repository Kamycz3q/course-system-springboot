package com.kamycz3q.coursesystemspringboot.personalData.controllers;


import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalDataCompany;
import com.kamycz3q.coursesystemspringboot.personalData.record.CreatePersonalCompanyRequest;
import com.kamycz3q.coursesystemspringboot.personalData.service.PersonalDataCompanyService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personal-data/company")
public class PersonalDataCompanyController {
    private final PersonalDataCompanyService personalDataCompanyService;

    @Autowired
    public PersonalDataCompanyController(PersonalDataCompanyService personalDataCompanyService) {
        this.personalDataCompanyService = personalDataCompanyService;
    }


    @GetMapping("")
    public List<PersonalDataCompany> listPersonalDataCompany() {
        return personalDataCompanyService.getPersonalDataCompanyList();
    }

    @GetMapping("/{id}")
    public PersonalDataCompany getPersonalDataCompany(@PathVariable("id") String id) {
        return personalDataCompanyService.getPersonalDataCompany(Long.parseLong(id));
    }

    @PostMapping("")
    public PersonalDataCompany createPersonalDataForCompany(@NotNull @RequestBody CreatePersonalCompanyRequest req) {
        return personalDataCompanyService.createPersonalDataCompany(req);
    }

    @PatchMapping("/{id}")
    public PersonalDataCompany createPersonalDataForCompany(@PathVariable("id") String id, @NotNull @RequestBody CreatePersonalCompanyRequest req) {
        return personalDataCompanyService.editPersonalDataCompany(Long.parseLong(id), req);
    }


    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") String id) {
        personalDataCompanyService.deletePersonalData(Long.parseLong(id));
    }

}
