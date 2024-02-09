package net.javaguides.todo.service.impl;

import jakarta.transaction.Transactional;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.dto.request.GradeRequestDTO;
import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.entity.Grade;
import net.javaguides.todo.entity.Student;
import net.javaguides.todo.entity.Teacher;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.GradeRepository;
import net.javaguides.todo.repository.StudentRepository;
import net.javaguides.todo.repository.TeacherRepository;
import net.javaguides.todo.service.GradeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public GradeResponseDTO addGrade(GradeRequestDTO gradeRequestDTO) {
        Grade grade = modelMapper.map(gradeRequestDTO, Grade.class);

        Long studentId = gradeRequestDTO.getStudent().getId();
        Student student;

        if (studentId != null && studentRepository.existsById(studentId)) {
            // If the student id is valid, retrieve the existing student
            student = studentRepository.findById(studentId).orElseThrow(); // Use appropriate exception handling
        } else {
            // If the id is not valid or not provided, create a new student
            StudentRequestDTO newStudentDTO = gradeRequestDTO.getStudent();
            student = modelMapper.map(newStudentDTO, Student.class);
            student = studentRepository.save(student);
        }

        grade.setStudent(student);

        Grade savedGrade = gradeRepository.save(grade);
        GradeResponseDTO savedGradeResponseDTO = modelMapper.map(savedGrade, GradeResponseDTO.class);
        return savedGradeResponseDTO;
    }


    @Override
    public GradeResponseDTO getGrade(Long id) {
        Grade grade = gradeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        GradeResponseDTO gradeResponseDTO = modelMapper.map(grade, GradeResponseDTO.class);
        return gradeResponseDTO;
    }

    @Override
    public List<GradeResponseDTO> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        List<GradeResponseDTO> gradeDTOS = grades.stream()
                .map((grade) -> modelMapper.map(grade, GradeResponseDTO.class))
                .collect(Collectors.toList());
        return gradeDTOS;
    }

    @Override
    public GradeResponseDTO updateGrade(GradeRequestDTO gradeRequestDTO, Long gradeId) {
        // Extract studentId from the GradeRequestDTO
        Long studentId = gradeRequestDTO.getStudent().getId();

        // Find the student
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        // Find the grade
        Grade existingGrade = gradeRepository
                .findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + gradeId));

        // Update the grade fields
        existingGrade.setScore(gradeRequestDTO.getScore());
        existingGrade.setSubject(gradeRequestDTO.getSubject());

        // Set the student in the grade
        existingGrade.setStudent(student);

        // Save the updated grade
        Grade updatedGrade = gradeRepository.save(existingGrade);

        // Map and return the response DTO
        GradeResponseDTO updatedGradeResponseDTO = modelMapper.map(updatedGrade, GradeResponseDTO.class);
        return updatedGradeResponseDTO;
    }


    @Override
    public void deleteGrade(Long id) {

        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        gradeRepository.deleteById(id);
    }

    @Override
    public List<GradeResponseDTO> getGradesByStudent(Long studentId) {
        List<Grade> gradesByStudentId = gradeRepository.getGradesByStudentId(studentId);
        List<GradeResponseDTO> gradesResponseDTOByStudentId = gradesByStudentId.stream()
                .map(grade -> modelMapper.map(grade, GradeResponseDTO.class))
                .collect(Collectors.toList());
        return gradesResponseDTOByStudentId;
    }

    @Override
    public List<GradeResponseDTO> getGradesBySubject(String subject) {
        List<Grade> gradesBySubject = gradeRepository.getGradesBySubject(subject);
        List<GradeResponseDTO> gradesResponseDTOBySubject = gradesBySubject.stream()
                .map(grade -> modelMapper.map(grade, GradeResponseDTO.class))
                .collect(Collectors.toList());

        return gradesResponseDTOBySubject;
    }
}
