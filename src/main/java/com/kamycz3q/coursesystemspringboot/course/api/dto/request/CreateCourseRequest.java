package com.kamycz3q.coursesystemspringboot.course.api.dto.request;

public record CreateCourseRequest (
    String name,
    String description,
    Float cost,
    Long lecturerId

){
}
