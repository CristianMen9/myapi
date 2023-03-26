package edu.unimagdalena.pw.myapi.repositories;

import org.springframework.data.jpa.repository.*;

import edu.unimagdalena.pw.myapi.entidades.Teacher;
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    
}
