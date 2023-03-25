package edu.unimagdalena.pw.myapi.api.dto;

import org.springframework.stereotype.Component;

import edu.unimagdalena.pw.myapi.entidades.Teacher;

@Component
public class TeacherMapper {
    public TeacherDto toDto(Teacher teacher){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setCodigo(teacher.getCodigo());

        return teacherDto;
    }
    public Teacher toEntity(TeacherDto teacherDto){
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setCodigo(teacherDto.getCodigo());
        return teacher;
    }

    public TeacherCreationDto toTeacherCreationDto(Teacher teacher){
        TeacherCreationDto teacherCreationDto = new TeacherCreationDto();
        teacherCreationDto.setId(teacher.getId());
        teacherCreationDto.setFirstName(teacher.getFirstName());
        teacherCreationDto.setLastName(teacher.getLastName());
        teacherCreationDto.setCodigo(teacher.getCodigo());

        return teacherCreationDto;
    }
    public Teacher toTeacherEntity(TeacherCreationDto teacherCreationDto){
        Teacher teacher = new Teacher();
        teacher.setId(teacherCreationDto.getId());
        teacher.setFirstName(teacherCreationDto.getFirstName());
        teacher.setLastName(teacherCreationDto.getLastName());
        teacher.setCodigo(teacherCreationDto.getCodigo());
        return teacher;
    }
}
