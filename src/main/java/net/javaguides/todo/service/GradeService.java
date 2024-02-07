package net.javaguides.todo.service;
import net.javaguides.todo.dto.request.GradeRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;

import java.util.List;

public interface GradeService {
    GradeResponseDTO addGrade(GradeRequestDTO gradeRequestDTO);

    GradeResponseDTO getGrade(Long id);

    List<GradeResponseDTO> getAllGrades();

    GradeResponseDTO updateGrade(GradeRequestDTO gradeRequestDTO, Long id);

    void deleteGrade(Long id);
    List<GradeResponseDTO> getGradesByStudent(Long studentId);

    List<GradeResponseDTO> getGradesBySubject(String subject);
}
