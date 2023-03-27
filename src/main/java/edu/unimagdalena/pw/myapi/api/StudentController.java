package edu.unimagdalena.pw.myapi.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.unimagdalena.pw.myapi.api.dto.StudentCreationDto;
import edu.unimagdalena.pw.myapi.api.dto.StudentDto;
import edu.unimagdalena.pw.myapi.api.dto.StudentMapper;
import edu.unimagdalena.pw.myapi.entidades.Student;
import edu.unimagdalena.pw.myapi.exceptions.DuplicateCodigoException;
import edu.unimagdalena.pw.myapi.services.StudentService;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;


    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentCreationDto>> findAll(){
        List<Student> students = studentService.findAll();
        List<StudentCreationDto> studentCreationDtos = students.stream()
                                                        .map(s -> studentMapper.toStudentCreationDto(s))
                                                        .collect(Collectors.toList());
        return ResponseEntity.ok().body(studentCreationDtos);                                                
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<StudentCreationDto>> find(@PathVariable("id") Long id){
        Optional<StudentCreationDto> student = studentService.findById(id)
                             .map(s -> studentMapper.toStudentCreationDto(s));
        return ResponseEntity.status(HttpStatus.FOUND).body(student);                     
    }

    @PostMapping("/students")
    public ResponseEntity<StudentCreationDto> create(@RequestBody StudentDto student){
        Student newStudent = studentMapper.toEntity(student);
        Student studentCreated = null;
        try{
            studentCreated = studentService.create(newStudent);
        } catch(Exception e){
            throw new DuplicateCodigoException();
        }

        StudentCreationDto studentCreationDto = studentMapper.toStudentCreationDto(studentCreated);

        URI location  = ServletUriComponentsBuilder.fromCurrentRequest()
                          .path("/{id}")
                          .buildAndExpand(studentCreationDto.getId())
                          .toUri();
        return ResponseEntity.created(location).body(studentCreationDto);                  
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentCreationDto> update(@PathVariable("id") Long id, @RequestBody StudentCreationDto student){
        Student studentToUpdate = studentMapper.toStudentEntity(student);
        return studentService.update(id, studentToUpdate)
             .map(studentUpdated -> ResponseEntity.ok().body(studentMapper.toStudentCreationDto(studentToUpdate)))
             .orElseGet(() -> {
                StudentCreationDto studentCreationDto = studentMapper.toStudentCreationDto(studentToUpdate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(studentCreationDto.getId())
                        .toUri();
        return ResponseEntity.created(location).body(studentCreationDto);                        
             });   
    }

    @DeleteMapping("/deletestudent/{id}")
    public void delete(@PathVariable("id") Long id){
        studentService.delete(id);
    }



    
    
}
