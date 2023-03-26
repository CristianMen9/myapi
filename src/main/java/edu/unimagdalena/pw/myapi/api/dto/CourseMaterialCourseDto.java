package edu.unimagdalena.pw.myapi.api.dto;

import edu.unimagdalena.pw.myapi.entidades.Course;
import lombok.Data;

@Data
public class CourseMaterialCourseDto {
    private Long id;
    private String url;
    private Course course;
    
}
