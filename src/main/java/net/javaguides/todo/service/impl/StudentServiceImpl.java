package net.javaguides.todo.service.impl;

import jakarta.transaction.Transactional;
import net.javaguides.todo.dto.request.GradeRequestDTO;
import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.request.TeacherRequestDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.dto.response.TeacherResponseDTO;
import net.javaguides.todo.entity.Grade;
import net.javaguides.todo.entity.Student;
import net.javaguides.todo.entity.Teacher;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.GradeRepository;
import net.javaguides.todo.repository.StudentRepository;
import net.javaguides.todo.repository.TeacherRepository;
import net.javaguides.todo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        // Map the DTO to the Student entity
        Student student = modelMapper.map(studentRequestDTO, Student.class);

        // Check if the teacher exists by ID
        Teacher teacher = null;
        if (studentRequestDTO.getTeacher() != null && studentRequestDTO.getTeacher().getId() > 0) {
            teacher = teacherRepository.findById(studentRequestDTO.getTeacher().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        }

        // If the teacher is not found, create a new teacher
        if (teacher == null) {
            teacher = modelMapper.map(studentRequestDTO.getTeacher(), Teacher.class);
            teacher = teacherRepository.save(teacher);
        }

        // Set the student's teacher
        student.setTeacher(teacher);

        // Save the student
        Student savedStudent = studentRepository.save(student);

        // Set the student for each grade
        List<Grade> grades = savedStudent.getGrades();
        if (grades != null) {
            grades.forEach(grade -> grade.setStudent(savedStudent));
        }

        // Save the grades
        List<Grade> savedGrades = gradeRepository.saveAll(grades);
        savedStudent.setGrades(savedGrades);

        // Map and return the response DTO
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
        // Retrieve the existing student from the database
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // Update basic student information (username, password)
        student.setUsername(studentRequestDTO.getUsername());
        student.setPassword(studentRequestDTO.getPassword());

        // Find the existing teacher by ID
        Long teacherId = studentRequestDTO.getTeacher().getId();
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);

        if (optionalTeacher.isPresent()) {
            // If the teacher exists, update its fields (except ID)
            Teacher existingTeacher = optionalTeacher.get();
            Teacher updatedTeacher = modelMapper.map(studentRequestDTO.getTeacher(), Teacher.class);

            // Update fields except ID
            existingTeacher.setUsername(updatedTeacher.getUsername());
            existingTeacher.setPassword(updatedTeacher.getPassword());

            student.setTeacher(existingTeacher);
        } else {
            // If the teacher does not exist, create a new one
            Teacher newTeacher = modelMapper.map(studentRequestDTO.getTeacher(), Teacher.class);
            student.setTeacher(teacherRepository.save(newTeacher));
        }

        // Update or add grades
        List<GradeRequestDTO> gradeRequestList = studentRequestDTO.getGrades();
        if (gradeRequestList != null && !gradeRequestList.isEmpty()) {
            List<Grade> updatedGrades = gradeRequestList.stream()
                    .map(gradeRequest -> modelMapper.map(gradeRequest, Grade.class))
                    .collect(Collectors.toList());

            // Replace existing grades with updated ones
            student.setGrades(updatedGrades);
        }

        // Save the updated student to the database
        Student updatedStudent = studentRepository.save(student);

        // Map and return the response DTO
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
        List<Student> studentsWithGradeAbove = studentRepository.findByGradesScoreGreaterThan(grade);
        List<StudentResponseDTO> studentsResponseDTOWithGradeAbove = studentsWithGradeAbove.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
        return studentsResponseDTOWithGradeAbove;
    }
}
