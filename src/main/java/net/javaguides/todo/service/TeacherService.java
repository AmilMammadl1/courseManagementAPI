package net.javaguides.todo.service;

import net.javaguides.todo.dto.request.TeacherRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.dto.response.TeacherResponseDTO;

import java.util.List;

public interface TeacherService {

    TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO getTeacher(Long id);

    List<TeacherResponseDTO> getAllTeachers();

    TeacherResponseDTO updateTeacher(TeacherRequestDTO teacherRequestDTO, Long id);

    void deleteTeacher(Long id);
    List<StudentResponseDTO> getStudentsByTeacher(Long teacherId);

    List<GradeResponseDTO> getGradesByTeacher(Long teacherId);
}
