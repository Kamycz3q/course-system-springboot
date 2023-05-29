package com.kamycz3q.coursesystemspringboot.course;


import com.kamycz3q.coursesystemspringboot.course.models.CourseDTO;
import com.kamycz3q.coursesystemspringboot.course.models.CreateCourseRequest;
import com.kamycz3q.coursesystemspringboot.course.enrollment.Enrollment;
import com.kamycz3q.coursesystemspringboot.course.enrollment.EnrollmentRepository;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerRepository;
import com.kamycz3q.coursesystemspringboot.exception.ApiNotFoundException;
import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerRepository;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, LecturerRepository lecturerRepository, EnrollmentRepository enrollmentRepository, CustomerRepository customerRepository) {

        this.courseRepository = courseRepository;
        this.lecturerRepository = lecturerRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.customerRepository = customerRepository;
    }

    public CourseDTO courseDTOFromCourse(@NotNull Course course) {
        return new CourseDTO(
                course.getName(),
                course.getDescription(),
                course.getCost(),
                course.getLecturerEntity(),
                course.getStartDate(),
                course.getEndDate()
        );
    }

    public com.kamycz3q.coursesystemspringboot.course.enrollment.models.EnrollmentDTO participantDTOFromPArticipant(@NonNull Enrollment participant) {
        Optional<CustomerEntity> customer = customerRepository.findById(participant.getCustomerId());
        if (customer.isEmpty()) {
            throw new ApiNotFoundException("Customer not found");
        }
        return new com.kamycz3q.coursesystemspringboot.course.enrollment.models.EnrollmentDTO(
                customer.get(),
                participant.getCourseId()
        );
    }

    public Course createCourse(@NotNull CreateCourseRequest req) {
        if (!lecturerRepository.existsById(req.lecturerId())) {
            throw new ApiNotFoundException("Lecturer not found");
        }
        Course course = new Course();
        course.setLecturerEntity(lecturerRepository.findById(req.lecturerId()).get());
        course.setName(req.name());
        course.setDescription(req.description());
        course.setCost(req.cost());
        return courseRepository.save(course);
    }

    public Enrollment enrollParticipant(Long customerId, Long courseId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ApiNotFoundException("Customer not found");
        }
        if (!courseRepository.existsById(courseId)) {
            throw  new ApiNotFoundException("Course not found");
        }
        Enrollment participant = new Enrollment();
        participant.setCustomerId(customerId);
        participant.setCourseId(courseId);
        return  enrollmentRepository.save(participant);
    }

    public List<Enrollment> listParticipantsForCourse(Long courseId) {
        return enrollmentRepository.findAllByCourseId(courseId);
    }

    public Course getCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ApiNotFoundException("Course not found");
        }
        return courseRepository.findById(courseId).get();

    }

    public Course updateCourse(CreateCourseRequest req, Long courseId) {
        if (!lecturerRepository.existsById(req.lecturerId())) {
            throw new ApiNotFoundException("Lecturer Not found");
        }
        if (!courseRepository.existsById(courseId)) {
            throw new ApiNotFoundException("Course not found");
        }
        Course course = courseRepository.findById(courseId).get();
        course.setName(req.name());
        course.setDescription(req.description());
        course.setLecturerEntity(lecturerRepository.findById(req.lecturerId()).get());
        course.setCost(req.cost());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ApiNotFoundException("Course not found");
        }
        courseRepository.deleteById(courseId);
    }
    // dla czlonka

    public void setStartDate(Long courseId, Timestamp timestamp) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isEmpty()) {
            throw new ApiNotFoundException("Course not found");
        }
        Course course = optionalCourse.get();
        course.setStartDate(timestamp);
        courseRepository.save(course);
    }

    //meetingi
//    public Meeting addCourseMeeting( CreateMeetingDTO createMeetingDTO) {
//        Meeting meeting = new Meeting();
//        meeting.setTime(createMeetingDTO.time());
//        meeting.setCourseId(createMeetingDTO.courseId());
//        meeting.setName(createMeetingDTO.name());
//        return meetingRepository.save(meeting);
//    }
//
//    public Meeting editCourseMeeting(Long meetingId, CreateMeetingDTO createMeetingDTO) {
//        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingId);
//        if (meetingOptional.isEmpty()) {
//            throw new ApiNotFoundException("Meeting not found");
//        }
//        Meeting meeting = meetingOptional.get();
//        meeting.setTime(createMeetingDTO.time());
//        meeting.setCourseId(createMeetingDTO.courseId());
//        meeting.setName(createMeetingDTO.name());
//        return meetingRepository.save(meeting);
//    }
//
//    public void deleteCourseMeeting(Long meetingId) {
//        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingId);
//        if (meetingOptional.isEmpty()) {
//            throw new ApiNotFoundException("Meeting not found");
//        }
//        meetingRepository.delete(meetingOptional.get());
//    }
//
//    public List<Meeting> getCourseMeetings(Long courseId) {
//        if(courseRepository.findById(courseId).isEmpty()) {
//            throw new ApiNotFoundException("Course not found");
//        }
//        return meetingRepository.findAllByCourse_Id(courseId);
//    }
//
//    public Meeting getClosestMeeting(Timestamp date, Long courseId) {
//        List<Meeting> meetings = getCourseMeetings(courseId);
//        Long closestTimestampDifference = null;
//        Meeting selectedMeeting = null;
//        for (Meeting meeting : meetings) {
//            long newDiff = meeting.getTime().getTime() - date.getTime();
//            if (closestTimestampDifference == null || closestTimestampDifference > newDiff) {
//                closestTimestampDifference = newDiff;
//                selectedMeeting = meeting;
//            }
//        }
//        return selectedMeeting;
//    }
}
