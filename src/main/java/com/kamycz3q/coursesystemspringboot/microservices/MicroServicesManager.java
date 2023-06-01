package com.kamycz3q.coursesystemspringboot.microservices;

import com.kamycz3q.coursesystemspringboot.course.enrollment.Enrollment;
import com.kamycz3q.coursesystemspringboot.course.enrollment.EnrollmentRepository;
import com.kamycz3q.coursesystemspringboot.course.persistence.CourseEntity;
import com.kamycz3q.coursesystemspringboot.course.persistence.CourseRepository;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerRepository;
import com.kamycz3q.coursesystemspringboot.microservices.enums.MicroServiceAction;
import com.kamycz3q.coursesystemspringboot.microservices.services.EmailingService;
import com.kamycz3q.coursesystemspringboot.microservices.services.SMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MicroServicesManager {
    private final CustomerRepository customerRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final SMSService smsService;
    private final EmailingService emailingService;

    public void performAction(MicroServiceAction action) {
        performAction(action, null);
    }
    public void performAction(MicroServiceAction action, HashMap<String,?> optionalArgs) {
        switch (action) {
            case COURSE_DATE_CHANGED -> {
                List<Enrollment> enrollmentList = enrollmentRepository.findAllByCourseId((Long) optionalArgs.get("courseId"));
                CourseEntity courseEntity = courseRepository.findById((Long) optionalArgs.get("courseId")).get();
                enrollmentList.forEach(enrollment -> {
                    CustomerEntity customerEntity = customerRepository.findById(enrollment.getCustomerId()).get();
                    smsService.sendMessage(customerEntity.getPersonalData().getContactData().getPhoneNumber(),"Course " + courseEntity.getName() + " Changed date to " + courseEntity.getStartDate() + " - " + courseEntity.getEndDate());
                });
            }
            case COURSE_FINISHED -> {
                List<Enrollment> enrollmentList = enrollmentRepository.findAllByCourseId((Long) optionalArgs.get("courseId"));
                CourseEntity courseEntity = courseRepository.findById((Long) optionalArgs.get("courseId")).get();
                enrollmentList.forEach(enrollment -> {
                    CustomerEntity customerEntity = customerRepository.findById(enrollment.getCustomerId()).get();
                    smsService.sendMessage(customerEntity.getPersonalData().getContactData().getPhoneNumber(), "Course" + courseEntity.getName() + " finished");
                    emailingService.sendMessage(customerEntity.getPersonalData().getContactData().getPhoneNumber(), "Certificate");
                });
            }

            case NEW_ENROLLMENT -> {
                Enrollment enrollment = enrollmentRepository.findById((Long) optionalArgs.get("enrollmentId")).get();
                CustomerEntity customer = customerRepository.findById((Long) optionalArgs.get("customerId")).get();
            }
        }
    }
}
