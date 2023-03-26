package edu.unimagdalena.pw.myapi.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class CourseStudentDto {
    private Long id;
    private String name;
    List<StudentDto> students;
    
}
