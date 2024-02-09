package net.javaguides.todo.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String username;
    @JsonIgnoreProperties("students")  // Add this annotation
    private TeacherResponseDTO teacher;
    private List<GradeResponseDTO> grades;
}
