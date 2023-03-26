package edu.unimagdalena.pw.myapi.api.dto;

import edu.unimagdalena.pw.myapi.entidades.Teacher;
import lombok.Data;

@Data
public class CourseTeacherDto {
    private Long id;
    private String name;
    private Teacher profe;
    
}
