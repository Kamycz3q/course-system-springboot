package com.kamycz3q.coursesystemspringboot.course.logic;


import com.kamycz3q.coursesystemspringboot.course.api.dto.CourseDTO;
import com.kamycz3q.coursesystemspringboot.course.api.dto.request.CreateCourseRequest;
import com.kamycz3q.coursesystemspringboot.course.enrollment.Enrollment;
import com.kamycz3q.coursesystemspringboot.course.enrollment.EnrollmentRepository;
import com.kamycz3q.coursesystemspringboot.course.enrollment.absence.Absence;
import com.kamycz3q.coursesystemspringboot.course.enrollment.absence.AbsenceRepository;
import com.kamycz3q.coursesystemspringboot.course.enrollment.absence.dto.AbsenceDTO;
import com.kamycz3q.coursesystemspringboot.course.enrollment.absence.dto.CreateAbsenceRequest;
import com.kamycz3q.coursesystemspringboot.course.enrollment.models.EnrollmentDTO;
import com.kamycz3q.coursesystemspringboot.course.persistence.CourseEntity;
import com.kamycz3q.coursesystemspringboot.course.persistence.CourseRepository;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerRepository;
import com.kamycz3q.coursesystemspringboot.exception.ApiNotFoundException;
import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerRepository;
import com.kamycz3q.coursesystemspringboot.microservices.MicroServicesManager;
import com.kamycz3q.coursesystemspringboot.microservices.enums.MicroServiceAction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CustomerRepository customerRepository;
    private final AbsenceRepository absenceRepository;
    private final MicroServicesManager microServicesManager;


    public CourseDTO courseDTOFromCourse(@NotNull CourseEntity courseEntity) {
        return CourseDTO.fromCourse(courseEntity);
    }

    public EnrollmentDTO participantDTOFromPArticipant(@NonNull Enrollment participant) {
        Optional<CustomerEntity> customer = customerRepository.findById(participant.getCustomerId());
        if (customer.isEmpty()) {
            throw new ApiNotFoundException("Customer not found");
        }
        return new com.kamycz3q.coursesystemspringboot.course.enrollment.models.EnrollmentDTO(
                customer.get(),
                participant.getCourseId()
        );
    }

    public CourseEntity createCourse(@NotNull CreateCourseRequest req) {
        if (!lecturerRepository.existsById(req.lecturerId())) {
            throw new ApiNotFoundException("Lecturer not found");
        }
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setLecturerEntity(lecturerRepository.findById(req.lecturerId()).get());
        courseEntity.setName(req.name());
        courseEntity.setDescription(req.description());
        courseEntity.setCost(req.cost());
        return courseRepository.save(courseEntity);
    }

    public Enrollment enrollParticipant(Long customerId, Long courseId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ApiNotFoundException("Customer not found");
        }
        checkIsCourseExists(courseId);
        Enrollment participant = new Enrollment();
        participant.setCustomerId(customerId);
        participant.setCourseId(courseId);
        microServicesManager.performAction(MicroServiceAction.COURSE_DATE_CHANGED, (HashMap<String, ?>) Map.of("courseId", courseId));
        return enrollmentRepository.save(participant);
    }

    public List<Enrollment> listParticipantsForCourse(Long courseId) {
        return enrollmentRepository.findAllByCourseId(courseId);
    }

    public CourseEntity getCourse(Long courseId) {
        checkIsCourseExists(courseId);
        return courseRepository.findById(courseId).get();

    }

    private void checkIsCourseExists(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ApiNotFoundException("Course not found");
        }
    }

    public CourseEntity updateCourse(CreateCourseRequest req, Long courseId) {
        if (!lecturerRepository.existsById(req.lecturerId())) {
            throw new ApiNotFoundException("Lecturer Not found");
        }
        checkIsCourseExists(courseId);
        CourseEntity courseEntity = courseRepository.findById(courseId).get();
        courseEntity.setName(req.name());
        courseEntity.setDescription(req.description());
        courseEntity.setLecturerEntity(lecturerRepository.findById(req.lecturerId()).get());
        courseEntity.setCost(req.cost());
        return courseRepository.save(courseEntity);
    }

    public void deleteCourse(Long courseId) {
        checkIsCourseExists(courseId);
        courseRepository.deleteById(courseId);
    }
    // dla czlonka

    public void setDate(Long courseId, Timestamp timestampStart, Timestamp timestampEnd) {
        checkIsCourseExists(courseId);
        CourseEntity courseEntity = courseRepository.findById(courseId).get();
        courseEntity.setStartDate(timestampStart);
        courseEntity.setEndDate(timestampEnd);
        courseRepository.save(courseEntity);
        microServicesManager.performAction(MicroServiceAction.COURSE_DATE_CHANGED, (HashMap<String, ?>) Map.of("courseId", courseId));
    }


    public ResponseEntity addAbsence(CreateAbsenceRequest req) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(req.courseId());
        Optional<Enrollment> optionalEnroll = enrollmentRepository.findByCourseIdAndCustomerId(req.courseId(), req.customerId());

        if (optionalCourse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }

        if (optionalEnroll.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer enrollment not found in this course");
        }

        Absence absence = new Absence();
        absence.setCourse(optionalCourse.get());
        absence.setEnrollment(optionalEnroll.get());
        absence.setStartTimestamp(req.startTimestamp());
        absence.setEndTimestamp(req.endTimestamp());

        return ResponseEntity.status(HttpStatus.OK)
                .body(AbsenceDTO.fromAbsence(absenceRepository.save(absence)));
    }

    public ResponseEntity removeAbsence(Long absenceId) {
        Optional<Absence> absenceOptional = absenceRepository.findById(absenceId);
        if (absenceOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Absence not found");
        }
        absenceRepository.delete(absenceOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Canceled absence");
    }


}
