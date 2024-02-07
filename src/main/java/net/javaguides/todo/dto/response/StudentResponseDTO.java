package net.javaguides.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.todo.dto.request.GradeRequestDTO;
import net.javaguides.todo.dto.request.TeacherRequestDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    private Long id;
    private String username;
    private TeacherResponseDTO teacher;
    private GradeResponseDTO grade;


}