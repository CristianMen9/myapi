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

import edu.unimagdalena.pw.myapi.api.dto.CourseMaterialCreationDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseMaterialDto;
import edu.unimagdalena.pw.myapi.api.dto.CourseMaterialMapper;
import edu.unimagdalena.pw.myapi.entidades.CourseMaterial;
import edu.unimagdalena.pw.myapi.exceptions.DuplicateCodigoException;
import edu.unimagdalena.pw.myapi.services.CourseMaterialService;

@RestController
@RequestMapping("/api/v1/coursematerial")
public class CourseMaterialController {
    private final CourseMaterialService courseMaterialService;
    private final CourseMaterialMapper courseMaterialMapper;


    public CourseMaterialController(CourseMaterialService courseMaterialService,
            CourseMaterialMapper courseMaterialMapper) {
        this.courseMaterialService = courseMaterialService;
        this.courseMaterialMapper = courseMaterialMapper;
    }

    @GetMapping("/coursematerials")
    public ResponseEntity<List<CourseMaterialCreationDto>> findAll(){
        List<CourseMaterial> courseMaterials = courseMaterialService.findAll();
        List<CourseMaterialCreationDto> courseMaterialCreationDtos = courseMaterials.stream()
                                                                      .map(cm -> courseMaterialMapper.toCourseMaterialCreationDto(cm))
                                                                      .collect(Collectors.toList());
        return ResponseEntity.ok().body(courseMaterialCreationDtos);                                                              
    }

    @GetMapping("/coursematerials/{id}")
    public ResponseEntity<Optional<CourseMaterialCreationDto>> find(@PathVariable("id") Long id){
        Optional<CourseMaterialCreationDto> coursematerial = courseMaterialService.find(id)
                            .map(cm -> courseMaterialMapper.toCourseMaterialCreationDto(cm));
        return ResponseEntity.status(HttpStatus.FOUND).body(coursematerial);                                                                                          
    }

    @PostMapping("/coursematerials")
    public ResponseEntity<CourseMaterialCreationDto> create(@RequestBody CourseMaterialDto courseMaterialDto){
        

        CourseMaterial newCourseMaterial = courseMaterialMapper.toEntity(courseMaterialDto);
        CourseMaterial courseMaterial = null;
        try{
            courseMaterial = courseMaterialService.create(newCourseMaterial);
        } catch (Exception e) {
            throw new DuplicateCodigoException();

        }
        CourseMaterialCreationDto courseMaterialCreationDto = courseMaterialMapper.toCourseMaterialCreationDto(courseMaterial);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(courseMaterialCreationDto.getId())
                        .toUri();

        return ResponseEntity.created(location).body(courseMaterialCreationDto);
    }

    @PutMapping("/coursematerials/{id}")
    public ResponseEntity<CourseMaterialCreationDto> update(@PathVariable("id") Long id, @RequestBody CourseMaterialCreationDto courseMaterialcDto){
        CourseMaterial courseMaterialtoUpdate = courseMaterialMapper.toCourseMaterialEntity(courseMaterialcDto);
        return courseMaterialService.update(id, courseMaterialtoUpdate)
               .map(courseMaterialUpdated -> ResponseEntity.ok().body(courseMaterialMapper.toCourseMaterialCreationDto(courseMaterialtoUpdate)))
               .orElseGet(() -> {
                   CourseMaterialCreationDto courseMaterialCreationDto = courseMaterialMapper.toCourseMaterialCreationDto(courseMaterialtoUpdate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                         .path("/{id}")
                         .buildAndExpand(courseMaterialCreationDto.getId())
                         .toUri();
        return ResponseEntity.created(location).body(courseMaterialCreationDto);                            
               });
    }

    @DeleteMapping("/deletecoursematerials/{id}")
    public void delete(@PathVariable ("id") Long id){
        courseMaterialService.delete(id);
    }






    

    
}
