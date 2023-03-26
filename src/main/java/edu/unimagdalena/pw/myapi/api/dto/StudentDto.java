package edu.unimagdalena.pw.myapi.api.dto;

import java.time.LocalDate;

import edu.unimagdalena.pw.myapi.entidades.Gender;
import lombok.Data;

@Data
public class StudentDto {
    private String firstname;
    private String lastname;
    private String codigo;
    private Gender  gender;
    private LocalDate birthdate;

}
