package net.javaguides.todo.service.impl;

import net.javaguides.todo.dto.StudentDTO;
import net.javaguides.todo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public StudentDTO getStudent(Long id) {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return null;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO, Long id) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public List<StudentDTO> getStudentsByTeacher(Long teacherId) {
        return null;
    }

    @Override
    public List<StudentDTO> getStudentsWithGradeAbove(Double grade) {
        return null;
    }
}
