package edu.unimagdalena.pw.myapi.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class TeacherCourseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String codigo;
    List<CourseDto> courses;

}
