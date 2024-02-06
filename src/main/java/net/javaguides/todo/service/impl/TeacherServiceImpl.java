package net.javaguides.todo.service.impl;

import net.javaguides.todo.dto.GradeDTO;
import net.javaguides.todo.dto.StudentDTO;
import net.javaguides.todo.dto.TeacherDTO;
import net.javaguides.todo.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Override
    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        return null;
    }

    @Override
    public TeacherDTO getTeacher(Long id) {
        return null;
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return null;
    }

    @Override
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO, Long id) {
        return null;
    }

    @Override
    public void deleteTeacher(Long id) {

    }

    @Override
    public List<StudentDTO> getStudentsByTeacher(Long teacherId) {
        return null;
    }

    @Override
    public List<GradeDTO> getGradesByTeacher(Long teacherId) {
        return null;
    }
}
