package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import edu.unimagdalena.pw.myapi.entidades.Course;
import edu.unimagdalena.pw.myapi.entidades.Student;
import edu.unimagdalena.pw.myapi.entidades.Teacher;
import edu.unimagdalena.pw.myapi.repositories.CourseRepository;
import edu.unimagdalena.pw.myapi.repositories.StudentRepository;
@Service
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> find(Long id) {
       return courseRepository.findById(id);
    }

    @Override
    public Course create(Course course) {
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        return courseRepository.save(newCourse);
    }

    @Override
    public Optional<Course> update(Long id, Course newCourse) {
       return courseRepository.findById(id)
        .map(courseInDB -> {
            courseInDB.setName(newCourse.getName());
            courseInDB.setProfe(newCourse.getProfe());
            courseInDB.setStudents(newCourse.getStudents());

            return courseRepository.save(courseInDB);
       });
    }

    @Override
    public void delete(Long id) {
       courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public Optional<Course> assignTeacher(Long id, Teacher teacher) {
        return courseRepository.findById(id)
        .map(course -> {
            course.setProfe(teacher);
            return courseRepository.save(course);
        });
    }

    @Override
    public Optional<Course> addStudent(Long idCourse, Student newStudent) {
        return courseRepository.findById(idCourse)
            .map(course -> {

                Optional<Student> studentInDb = studentRepository.findByCodigo(newStudent.getCodigo());
                if(studentInDb.isPresent()){
                    course.getStudents().add(newStudent);
                }else{
                    Student student = studentRepository.save(newStudent);
                    course.getStudents().add(student);
                }
              
                return courseRepository.save(course);
            });
    }
    
}
