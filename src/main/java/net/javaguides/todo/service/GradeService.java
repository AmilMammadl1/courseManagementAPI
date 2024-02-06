package net.javaguides.todo.service;

import net.javaguides.todo.dto.GradeDTO;
import net.javaguides.todo.dto.StudentDTO;
import net.javaguides.todo.dto.TodoDto;

import java.util.List;

public interface GradeService {
    GradeDTO addGrade(GradeDTO gradeDTO);

    GradeDTO getGrade(Long id);

    List<GradeDTO> getAllGrades();

    GradeDTO updateGrade(GradeDTO gradeDTO, Long id);

    void deleteGrade(Long id);
    List<GradeDTO> getGradesByStudent(Long studentId);

    List<GradeDTO> getGradesBySubject(String subject);
}
