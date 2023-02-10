package com.mgt.studentmgt.student;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;


    public Student(String name, Gender gender, String email) {
        this.name = name;
        this.gender = gender;
        this.email = email;
    }
}
