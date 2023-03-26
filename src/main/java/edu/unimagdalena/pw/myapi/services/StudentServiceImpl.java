package edu.unimagdalena.pw.myapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unimagdalena.pw.myapi.entidades.Course;
import edu.unimagdalena.pw.myapi.entidades.Student;
import edu.unimagdalena.pw.myapi.repositories.CourseRepository;
import edu.unimagdalena.pw.myapi.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    private final  CourseRepository courseRepository;
    private final  StudentRepository studentRepository;

    

    public StudentServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        Student newStudent = new Student();
        newStudent.setId(student.getId());
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setCodigo(student.getCodigo());
        newStudent.setGender(student.getGender());
        newStudent.setBirthDate(student.getBirthDate());
        newStudent.setCourses(student.getCourses());

        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> update(Long id, Student newStudent) {
        return studentRepository.findById(id)
        .map(studentd -> {
        studentd.setFirstName(newStudent.getFirstName());
        studentd.setLastName(newStudent.getLastName());
        studentd.setCodigo(newStudent.getCodigo());
        studentd.setGender(newStudent.getGender());
        studentd.setBirthDate(newStudent.getBirthDate());
        studentd.setCourses(newStudent.getCourses());

        return studentRepository.save(studentd);
        });
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> addCourse(Long id, Course newCourse) {
        return studentRepository.findById(id)
        .map(student -> {

            Optional<Course> course = courseRepository.findById(newCourse.getId());
            if(course.isPresent()){
                student.getCourses().add(newCourse);
            }else{
                Course coursed = courseRepository.save(newCourse);
                student.getCourses().add(coursed);
            }

            return studentRepository.save(student);
        });
    }
    
}
