package edu.unimagdalena.pw.myapi.exceptions;

public class TeacherNotFoundException extends RuntimeException{

    public TeacherNotFoundException() {
        super("Teacher not found");
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
    
}
