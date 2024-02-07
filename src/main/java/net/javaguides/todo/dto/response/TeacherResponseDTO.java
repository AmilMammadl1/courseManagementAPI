package net.javaguides.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import net.javaguides.todo.dto.request.GradeRequestDTO;
import net.javaguides.todo.dto.request.StudentRequestDTO;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDTO {
    private Long id;
    private String username;
    private List<StudentRequestDTO> students;
    private List<GradeRequestDTO> grades;
}
