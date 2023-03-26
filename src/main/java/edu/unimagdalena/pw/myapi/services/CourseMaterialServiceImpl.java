package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unimagdalena.pw.myapi.entidades.Course;
import edu.unimagdalena.pw.myapi.entidades.CourseMaterial;
import edu.unimagdalena.pw.myapi.repositories.CourseMaterialRepository;
import edu.unimagdalena.pw.myapi.repositories.CourseRepository;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;
    private final  CourseRepository courseRepository;

    

    public CourseMaterialServiceImpl(CourseMaterialRepository courseMaterialRepository,
            CourseRepository courseRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseMaterial> findAll() {
        return courseMaterialRepository.findAll();
    }

    @Override
    public Optional<CourseMaterial> find(Long id) {
        return courseMaterialRepository.findById(id);
    }

    @Override
    public CourseMaterial create(CourseMaterial coursematerial) {
        CourseMaterial newcourseMaterial = new CourseMaterial();
        newcourseMaterial.setUrl(coursematerial.getUrl());
        newcourseMaterial.setCourse(coursematerial.getCourse());

        return courseMaterialRepository.save(coursematerial);
    }

    @Override
    public Optional<CourseMaterial> update(Long id, CourseMaterial newCoursematerial) {
       return courseMaterialRepository.findById(id)
       .map(coursemateriald -> {
        coursemateriald.setUrl(newCoursematerial.getUrl());
        coursemateriald.setCourse(newCoursematerial.getCourse());

        return courseMaterialRepository.save(coursemateriald);
       });
    }

    @Override
    public void delete(Long id) {
        courseMaterialRepository.deleteById(id);
    }

    @Override
    public Optional<CourseMaterial> addCourse(Long id, Course course) {
        return courseMaterialRepository.findById(id)
        .map(coursemateriald -> {
            Optional<Course>  Course = courseRepository.findById(course.getId());
            if(Course.isPresent()){
                coursemateriald.setCourse(Course.get());
        }else{
            Course courses = courseRepository.save(course);
            coursemateriald.setCourse(courses);
        }
        return courseMaterialRepository.save(coursemateriald);
    });
}
    
}
