package edu.unimagdalena.pw.myapi.api.dto;

import java.time.LocalDate;
import java.util.List;

import edu.unimagdalena.pw.myapi.entidades.Gender;
import lombok.Data;

@Data
public class StudentCourseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String codigo;
    private Gender  gender;
    private LocalDate birthdate;
    List<CourseDto> courses;
    
}
