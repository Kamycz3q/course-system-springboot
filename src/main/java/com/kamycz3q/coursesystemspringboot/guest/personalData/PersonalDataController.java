package com.kamycz3q.coursesystemspringboot.guest.personalData;


import com.kamycz3q.coursesystemspringboot.guest.personalData.models.CreatePersonalRequest;
import com.kamycz3q.coursesystemspringboot.guest.personalData.models.PersonalDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personal-data")
public class PersonalDataController {
    private final PersonalDataService personalDataService;

    @Autowired
    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @GetMapping()
    public List<PersonalDataDTO> listPersonalData() {
        return personalDataService.getPersonalDataList();
    }

    @GetMapping("/{id}")
    public PersonalDataDTO getPersonalData(@PathVariable("id") String id) {
        return personalDataService.getPersonalData(Long.parseLong(id));
    }

    @PostMapping("")
    public PersonalDataDTO addPersonalData(@NonNull @RequestBody CreatePersonalRequest createPersonalRequest) {
        return personalDataService.createPersonalData(createPersonalRequest);
    }
    @PatchMapping("/{id}")
    public PersonalDataDTO editPersonalData(@PathVariable("id") String id, @NonNull @RequestBody CreatePersonalRequest createPersonalRequest) {
        return personalDataService.editPersonalData(Long.parseLong(id), createPersonalRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalData(@PathVariable("id") String id) {
        personalDataService.deletePersonalData(Long.parseLong(id));
    }
}
