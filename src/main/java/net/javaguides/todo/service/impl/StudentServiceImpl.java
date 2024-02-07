package net.javaguides.todo.service.impl;

import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.entity.Grade;
import net.javaguides.todo.entity.Student;
import net.javaguides.todo.entity.Teacher;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.StudentRepository;
import net.javaguides.todo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        StudentResponseDTO savedStudentResponseDTO = modelMapper.map(savedStudent, StudentResponseDTO.class);
        return savedStudentResponseDTO;

    }

    @Override
    public StudentResponseDTO getStudent(Long id) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        StudentResponseDTO studentResponseDTO = modelMapper.map(student, StudentResponseDTO.class);
        return studentResponseDTO;
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOList = studentList.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
        return studentResponseDTOList;
    }

    @Override
    public StudentResponseDTO updateStudent(StudentRequestDTO studentRequestDTO, Long id) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        student.setGrade(modelMapper.map(studentRequestDTO.getGrade(),Grade.class));
        student.setUsername(studentRequestDTO.getUsername());
        student.setPassword(studentRequestDTO.getPassword());
        student.setTeacher(modelMapper.map(studentRequestDTO.getTeacher(), Teacher.class));

        Student updatedStudent = studentRepository.save(student);
        StudentResponseDTO updatedStudentDTO = modelMapper.map(updatedStudent, StudentResponseDTO.class);
        return updatedStudentDTO;
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponseDTO> getStudentsByTeacher(Long teacherId) {
        List<StudentResponseDTO> byTeacherId = studentRepository.findByTeacherId(teacherId);
        return byTeacherId;
    }

    @Override
    public List<StudentResponseDTO> getStudentsWithGradeAbove(Double grade) {
        List<Student> studentsWithGradeAbove = studentRepository.getStudentsWithGradeAbove(grade);
        List<StudentResponseDTO> studentsResponseDTOWithGradeAbove = studentsWithGradeAbove.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
        return studentsResponseDTOWithGradeAbove;
    }
}
