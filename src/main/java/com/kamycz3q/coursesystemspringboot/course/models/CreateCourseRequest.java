package com.kamycz3q.coursesystemspringboot.course.models;

public record CreateCourseRequest (
    String name,
    String description,
    Float cost,
    Long lecturerId

){
}
