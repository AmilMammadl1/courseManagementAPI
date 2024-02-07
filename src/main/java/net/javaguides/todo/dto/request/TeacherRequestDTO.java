package net.javaguides.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.todo.dto.response.GradeResponseDTO;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequestDTO {
    private String username;
    private String password;
    private List<StudentRequestDTO> students;
    private List<GradeResponseDTO> grades;
}
