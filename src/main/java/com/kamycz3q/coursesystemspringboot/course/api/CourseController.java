package com.kamycz3q.coursesystemspringboot.course.api;


import com.kamycz3q.coursesystemspringboot.course.enrollment.absence.dto.CreateAbsenceRequest;
import com.kamycz3q.coursesystemspringboot.course.logic.CourseService;
import com.kamycz3q.coursesystemspringboot.course.api.dto.CourseDTO;
import com.kamycz3q.coursesystemspringboot.course.api.dto.CreateCourseRequest;
import com.kamycz3q.coursesystemspringboot.course.enrollment.Enrollment;
import com.kamycz3q.coursesystemspringboot.course.enrollment.models.EnrollmentDTO;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CourseController(CourseService courseService, CustomerRepository customerRepository) {
        this.courseService = courseService;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CreateCourseRequest req) {
        return  courseService.courseDTOFromCourse(courseService.createCourse(req));
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long id) {
        return  courseService.courseDTOFromCourse(courseService.getCourse(id));
    }

    @PatchMapping("/{id}")
    public CourseDTO updateCourseById(@RequestBody CreateCourseRequest req, @PathVariable("id") Long id){
        return courseService.courseDTOFromCourse(courseService.updateCourse(req, id));
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }

    @PostMapping("/enroll/{customerId}/{courseId}")
    public EnrollmentDTO enrollCustomerById(@PathVariable("customerId") Long customerId, @PathVariable Long courseId ) {
        Enrollment enrollment = courseService.enrollParticipant(customerId, courseId);

        return new EnrollmentDTO(customerRepository.findById(customerId).get(),
                courseId);
    }

    @PatchMapping("/edit/{id}")
    public void editCourseById(@PathVariable("id") Long id, @RequestBody CreateCourseRequest req) {
        courseService.updateCourse(req, id);
    }

    @PatchMapping("/edit/setDate/{id}/{startTimestamp}/{endTimestamp}")
    public void setDate(@PathVariable("id") Long id, @PathVariable("startTimestamp") Timestamp startTimestamp, @PathVariable("endTimestamp") Timestamp endTimestamp) {
        courseService.setDate(id, startTimestamp, endTimestamp);
    }


    //absence
    @PostMapping("/edit/absence")
    public ResponseEntity<String> addAbsence(@RequestBody CreateAbsenceRequest req) {
        return courseService.addAbsence(req);
    }

    @DeleteMapping("/delete/absence/{id}")
    public ResponseEntity<String> deleteAbsence(@PathVariable("id") Long id) {
        return courseService.removeAbsence(id);
    }



}
