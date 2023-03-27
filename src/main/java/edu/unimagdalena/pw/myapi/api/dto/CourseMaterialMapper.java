package edu.unimagdalena.pw.myapi.api.dto;

import org.springframework.stereotype.Component;

import edu.unimagdalena.pw.myapi.entidades.CourseMaterial;

@Component
public class CourseMaterialMapper {
    public CourseMaterialDto toDto(CourseMaterial courseMaterial){
        CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
        courseMaterialDto.setUrl(courseMaterial.getUrl());

        return courseMaterialDto;
    }

    public CourseMaterial toEntity(CourseMaterialDto courseMaterialDto){
        CourseMaterial courseMaterial = new CourseMaterial();
        courseMaterial.setUrl(courseMaterialDto.getUrl());

        return courseMaterial;
    }

    public CourseMaterialCreationDto toCourseMaterialCreationDto(CourseMaterial courseMaterial){
        CourseMaterialCreationDto courseMaterialCreationDto = new CourseMaterialCreationDto();
        courseMaterialCreationDto.setId(courseMaterial.getId());
        courseMaterialCreationDto.setUrl(courseMaterial.getUrl());

        return courseMaterialCreationDto;
    }

    public CourseMaterial toCourseMaterialEntity(CourseMaterialCreationDto courseMaterialCreationDto){
        CourseMaterial courseMaterial = new CourseMaterial();
        courseMaterial.setId(courseMaterialCreationDto.getId());
        courseMaterial.setUrl(courseMaterialCreationDto.getUrl());

        return courseMaterial;
    }
    
}

