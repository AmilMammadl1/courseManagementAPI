package net.javaguides.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.response.TeacherResponseDTO;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeResponseDTO {
    private Long id;
    private String subject;
    private Double grade;
    private TeacherResponseDTO teacher;
    private StudentRequestDTO student;
}
