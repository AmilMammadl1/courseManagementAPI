package net.javaguides.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.todo.dto.response.GradeResponseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {

    private String username;
    private String password;
    private TeacherRequestDTO teacher;
    private GradeRequestDTO grade;
}
