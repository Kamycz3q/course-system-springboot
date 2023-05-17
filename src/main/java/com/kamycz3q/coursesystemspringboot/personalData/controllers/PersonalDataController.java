package com.kamycz3q.coursesystemspringboot.personalData.controllers;


import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalData;
import com.kamycz3q.coursesystemspringboot.personalData.record.CreatePersonalRequest;
import com.kamycz3q.coursesystemspringboot.personalData.service.PersonalDataService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personal-data/personal")
public class PersonalDataController {
    private final PersonalDataService personalDataService;

    @Autowired
    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @GetMapping()
    public List<PersonalData> listPersonalData() {
        return personalDataService.getPersonalDataList();
    }

    @GetMapping("/{id}")
    public PersonalData getPersonalData(@PathVariable("id") String id) {
        return personalDataService.getPersonalData(Long.parseLong(id));
    }

    @PostMapping("")
    public PersonalData addPersonalData(@NotNull @RequestBody CreatePersonalRequest createPersonalRequest) {
        return personalDataService.createPersonalData(createPersonalRequest);
    }
    @PatchMapping("/{id}")
    public PersonalData editPersonalData(@PathVariable("id") String id, @NotNull @RequestBody CreatePersonalRequest createPersonalRequest) {
        return personalDataService.editPersonalData(Long.parseLong(id), createPersonalRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalData(@PathVariable("id") String id) {
        personalDataService.deletePersonalData(Long.parseLong(id));
    }
}
