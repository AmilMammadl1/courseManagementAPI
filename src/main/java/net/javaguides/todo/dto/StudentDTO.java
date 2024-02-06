package net.javaguides.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private TeacherDTO teacher;
    private GradeDTO grade;


    // Constructors, getters, setters, etc.
}