package com.kamycz3q.coursesystemspringboot.lecturer.api;


import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerEntity;
import com.kamycz3q.coursesystemspringboot.lecturer.logic.LecturerService;
import com.kamycz3q.coursesystemspringboot.lecturer.api.dto.CreateLecturerAccountRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lecturer")
public class LecturerController {
    private final LecturerService lecturerService;

    @Autowired
    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public List<LecturerEntity> lecturerList() {
        return lecturerService.lecturerList();
    }

    @GetMapping("/{id}")
    public LecturerEntity getLecturer(@PathVariable("id") String id) throws Exception {
        return lecturerService.getLecturerAccount(Long.valueOf(id));
    }

    @DeleteMapping("/{id}")
    public void deleteLecturer(@PathVariable("id") String id) throws Exception {
        lecturerService.deleteLecturerAccount(Long.valueOf(id));
    }

    @PostMapping()
    public LecturerEntity createLecturerFromUser(@NotNull @RequestBody CreateLecturerAccountRequest createLecturerAccountRequest) throws Exception {
        return lecturerService.createLecturerFromUser(createLecturerAccountRequest.customerId(), createLecturerAccountRequest.displayName());
    }

    @PatchMapping("/{id}/{newName}")
    public void updateLecturerDisplayName(@PathVariable("id") String id, @PathVariable("newName") String newName) throws Exception {
        lecturerService.changeLecturerDisplayName(Long.valueOf(id), newName);
    }
}
