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

import edu.unimagdalena.pw.myapi.api.dto.CourseCreationDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseMapper;
import edu.unimagdalena.pw.myapi.entidades.Course;
import edu.unimagdalena.pw.myapi.exceptions.DuplicateCodigoException;
import edu.unimagdalena.pw.myapi.services.CourseService;

@RestController
@RequestMapping("/api/v1/")
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseCreationDto>> findAll(){
        List<Course> courses = courseService.findAll();
        List<CourseCreationDto> courseCreationDtos = courses.stream()
                                                       .map(c -> courseMapper.toCourseCreationDto(c))
                                                       .collect(Collectors.toList());
        return ResponseEntity.ok().body(courseCreationDtos);                                               
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Optional<CourseCreationDto>> find(@PathVariable("id") Long id){
        Optional<CourseCreationDto> course = courseService.find(id)
                                    .map(c -> courseMapper.toCourseCreationDto(c));
        return ResponseEntity.status(HttpStatus.FOUND).body(course);                            
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseCreationDto> create(@RequestBody CourseDto course){
        Course newCourse = courseMapper.toEntity(course);
        Course courseCreated = null;

        try{
            courseCreated = courseService.create(newCourse);
        } catch(Exception e){
            throw new DuplicateCodigoException();
        }

        CourseCreationDto courseCreationDto = courseMapper.toCourseCreationDto(courseCreated);

        URI location =  ServletUriComponentsBuilder.fromCurrentRequest()
                          .path("/{id}")
                          .buildAndExpand(courseCreationDto.getId())
                          .toUri();
        return ResponseEntity.created(location).body(courseCreationDto);                  
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseCreationDto> update(@PathVariable("Id") Long id, @RequestBody CourseCreationDto course){
        Course courseUpdate = courseMapper.toCourseEntity(course);
        return courseService.update(id, courseUpdate)
             .map(courseUpdated -> ResponseEntity.ok().body(courseMapper.toCourseCreationDto(courseUpdate)))
             .orElseGet(() -> {
                CourseCreationDto courseCreationDto = courseMapper.toCourseCreationDto(courseUpdate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                         .path("/{id}")
                         .buildAndExpand(courseCreationDto.getId())
                         .toUri();
        return ResponseEntity.created(location).body(courseCreationDto);                         
             });     
    }

    @DeleteMapping("/deletecourses/{id}")
    public void delete(@PathVariable("id")Long id){
        courseService.delete(id);
    }

    
    
}
