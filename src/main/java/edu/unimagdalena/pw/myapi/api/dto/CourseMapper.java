package edu.unimagdalena.pw.myapi.api.dto;

import org.springframework.stereotype.Component;

import edu.unimagdalena.pw.myapi.entidades.Course;

@Component
public class CourseMapper {
    public CourseDto toDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setName(course.getName());

        return courseDto;
    }

    public Course toEntity(CourseDto courseDto){
        Course course = new Course();
        course.setName(courseDto.getName());

        return course;
    }

    public CourseCreationDto toCourseCreationDto(Course course){
        CourseCreationDto courseCreationDto = new CourseCreationDto();
        courseCreationDto.setId(course.getId());
        courseCreationDto.setName(course.getName());

        return courseCreationDto;
    }

    public Course toCourseEntity(CourseCreationDto courseCreationDto){
        Course course = new Course();
        course.setId(courseCreationDto.getId());
        course.setName(courseCreationDto.getName());

        return course;
    }
    
}
