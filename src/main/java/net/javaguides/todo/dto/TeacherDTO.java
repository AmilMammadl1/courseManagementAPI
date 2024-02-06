package net.javaguides.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long id;
    private String username;
    private String password;
    private List<StudentDTO> students;
    private List<GradesDTO> grades;

    // Constructors, getters, setters, etc.
}