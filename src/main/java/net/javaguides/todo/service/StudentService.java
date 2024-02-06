package net.javaguides.todo.service;

import net.javaguides.todo.dto.StudentDTO;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Student;

import java.util.List;

public interface StudentService {

    StudentDTO addStudent(StudentDTO studentDTO);

    StudentDTO getStudent(Long id);

    List<StudentDTO> getAllStudents();

    StudentDTO updateStudent(StudentDTO studentDTO, Long id);

    void deleteStudent(Long id);

    List<StudentDTO> getStudentsByTeacher(Long teacherId);

    List<StudentDTO> getStudentsWithGradeAbove(Double grade);

}
