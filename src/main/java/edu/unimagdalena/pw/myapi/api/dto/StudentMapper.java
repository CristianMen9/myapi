package edu.unimagdalena.pw.myapi.api.dto;

import org.springframework.stereotype.Component;

import edu.unimagdalena.pw.myapi.entidades.Student;

@Component
public class StudentMapper {
    public StudentDto toDto(Student student){
        StudentDto studentdto = new StudentDto();
        studentdto.setFirstname(student.getFirstName());
        studentdto.setLastname(student.getLastName());
        studentdto.setCodigo(student.getCodigo());
        studentdto.setGender(student.getGender());
        studentdto.setBirthdate(student.getBirthDate());

        return studentdto;
    }

    public Student toEntity(StudentDto studentdto){
        Student student = new Student();
        student.setFirstName(studentdto.getFirstname());
        student.setLastName(studentdto.getLastname());
        student.setCodigo(studentdto.getCodigo());
        student.setGender(studentdto.getGender());
        student.setBirthDate(studentdto.getBirthdate());

        return student;
    }

    public StudentCreationDto toStudentCreationDto(Student student){
        StudentCreationDto studentcreationdto = new StudentCreationDto();
        studentcreationdto.setId(student.getId());
        studentcreationdto.setFirstname(student.getFirstName());
        studentcreationdto.setLastname(student.getLastName());
        studentcreationdto.setCodigo(student.getCodigo());
        studentcreationdto.setGender(student.getGender());
        studentcreationdto.setBirthdate(student.getBirthDate());
        
        return studentcreationdto;
    }

    public Student toStudentEntity(StudentCreationDto studentCreationDto){
        Student student = new Student();
        student.setId(studentCreationDto.getId());
        student.setFirstName(studentCreationDto.getFirstname());
        student.setLastName(studentCreationDto.getLastname());
        student.setCodigo(studentCreationDto.getCodigo());
        student.setGender(studentCreationDto.getGender());
        student.setBirthDate(studentCreationDto.getBirthdate());

        return student;
    }
    
}
