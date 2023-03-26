package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.pw.myapi.entidades.Course;
import edu.unimagdalena.pw.myapi.entidades.CourseMaterial;

public interface CourseMaterialService {
    List<CourseMaterial> findAll();
    Optional<CourseMaterial> find(Long id);
    CourseMaterial create(CourseMaterial coursematerial);
    Optional<CourseMaterial> update(Long id, CourseMaterial newCoursematerial);
    void delete(Long id);
    Optional<CourseMaterial> addCourse(Long id, Course course);

    
}
