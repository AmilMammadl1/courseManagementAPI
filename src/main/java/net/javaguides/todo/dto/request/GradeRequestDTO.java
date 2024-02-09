package net.javaguides.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class GradeRequestDTO {
    private String subject;
    private Double score;
    private StudentRequestDTO student;
}