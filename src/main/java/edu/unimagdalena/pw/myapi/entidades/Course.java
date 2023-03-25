package edu.unimagdalena.pw.myapi.entidades;


import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher profe;

    @ManyToMany
    @JoinTable(name="COURSES_STUDENTS", 
    joinColumns=@JoinColumn(name="course_id", referencedColumnName="id" ),
    inverseJoinColumns=@JoinColumn(name="student_id", referencedColumnName="id"))
    private Set<Student> students;
    
    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;

  

    

}
