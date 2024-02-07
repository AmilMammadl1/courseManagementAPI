package net.javaguides.todo.service;

import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO getStudent(Long id);

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO updateStudent(StudentRequestDTO studentDTO, Long id);

    void deleteStudent(Long id);

    List<StudentResponseDTO> getStudentsByTeacher(Long teacherId);

    List<StudentResponseDTO> getStudentsWithGradeAbove(Double grade);

}
