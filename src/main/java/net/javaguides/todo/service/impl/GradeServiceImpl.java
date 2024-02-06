package net.javaguides.todo.service.impl;

import net.javaguides.todo.dto.GradeDTO;
import net.javaguides.todo.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    @Override
    public GradeDTO addGrade(GradeDTO gradeDTO) {
        return null;
    }

    @Override
    public GradeDTO getGrade(Long id) {
        return null;
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        return null;
    }

    @Override
    public GradeDTO updateGrade(GradeDTO gradeDTO, Long id) {
        return null;
    }

    @Override
    public void deleteGrade(Long id) {

    }

    @Override
    public List<GradeDTO> getGradesByStudent(Long studentId) {
        return null;
    }

    @Override
    public List<GradeDTO> getGradesBySubject(String subject) {
        return null;
    }
}
