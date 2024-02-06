package net.javaguides.todo.service;

import net.javaguides.todo.dto.GradeDTO;
import net.javaguides.todo.dto.StudentDTO;
import net.javaguides.todo.dto.TeacherDTO;
import net.javaguides.todo.dto.TodoDto;

import java.util.List;

public interface TeacherService {

    TeacherDTO addTeacher(TeacherDTO teacherDTO);

    TeacherDTO getTeacher(Long id);

    List<TeacherDTO> getAllTeachers();

    TeacherDTO updateTeacher(TeacherDTO teacherDTO, Long id);

    void deleteTeacher(Long id);
    List<StudentDTO> getStudentsByTeacher(Long teacherId);

    List<GradeDTO> getGradesByTeacher(Long teacherId);
}
