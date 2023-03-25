package edu.unimagdalena.pw.myapi.exceptions;

import java.time.LocalDateTime;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponse> teacherNotFoundHandler( TeacherNotFoundException ex, 
                            WebRequest wr){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        error.setDescription(wr.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DuplicateCodigoException.class)
    public ResponseEntity<ErrorResponse> codigoDuplicado (DuplicateCodigoException ex, 
                                    WebRequest webRequest){
        ErrorResponse error = new ErrorResponse(
                                    HttpStatus.CONFLICT.value(), 
                                    LocalDateTime.now(), 
                                    ex.getMessage(),
                                    webRequest.getDescription(false)
                                    );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
