package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.pw.myapi.entidades.Course;
import edu.unimagdalena.pw.myapi.entidades.Student;

public interface StudentService {
    Student create(Student student);
    Optional<Student> update(Long id, Student newStudent);
    Optional<Student> findById(Long id);
    List<Student> findAll();
    void delete(Long id);
    Optional<Student> addCourse(Long id, Course newCourse);
}
