package net.javaguides.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradesDTO {

    private Long id;
    private String subject;
    private Double grade;
    private TeacherDTO teacher;
    private StudentDTO student;


}