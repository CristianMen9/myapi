package edu.unimagdalena.pw.myapi.api.dto;

import edu.unimagdalena.pw.myapi.entidades.CourseMaterial;
import lombok.Data;

@Data
public class CourseCourseMaterialDto {
    private Long id;
    private String name;
    private CourseMaterial courseMaterial;
    
}
