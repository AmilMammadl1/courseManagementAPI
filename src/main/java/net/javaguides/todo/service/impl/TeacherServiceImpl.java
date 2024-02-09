package net.javaguides.todo.service.impl;

import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.request.TeacherRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.dto.response.TeacherResponseDTO;
import net.javaguides.todo.entity.Grade;
import net.javaguides.todo.entity.Student;
import net.javaguides.todo.entity.Teacher;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.StudentRepository;
import net.javaguides.todo.repository.TeacherRepository;
import net.javaguides.todo.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO) {
        // Map the DTO to the Teacher entity
        Teacher teacher = modelMapper.map(teacherRequestDTO, Teacher.class);

        // Save the teacher without students to get the teacher ID
        Teacher savedTeacher = teacherRepository.save(teacher);

        // Set the teacher ID for each student
        List<Student> students = teacher.getStudents();
        if (students != null) {
            students.forEach(student -> {
                student.setTeacher(savedTeacher);

                // Set the student for each grade
                List<Grade> grades = student.getGrades();
                if (grades != null) {
                    grades.forEach(grade -> grade.setStudent(student));
                }
            });
        }
        // Save the students with the updated teacher ID
        List<Student> savedStudents = studentRepository.saveAll(students);

        // Set the students with updated IDs to the saved teacher
        savedTeacher.setStudents(savedStudents);

        // Map the saved teacher to DTO
        TeacherResponseDTO savedTeacherResponseDTO = modelMapper.map(savedTeacher, TeacherResponseDTO.class);

        return savedTeacherResponseDTO;
    }



    @Override
    public TeacherResponseDTO getTeacher(Long id) {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        TeacherResponseDTO teacherResponseDTO = modelMapper.map(teacher, TeacherResponseDTO.class);
        return teacherResponseDTO;
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        List<Teacher> all = teacherRepository.findAll();
        List<TeacherResponseDTO> teacherResponseDTOList = all.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherResponseDTO.class))
                .collect(Collectors.toList());
        return teacherResponseDTOList;
    }


    @Override
    public TeacherResponseDTO updateTeacher(TeacherRequestDTO teacherRequestDTO, Long id) {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));

        List<Student> students = teacherRequestDTO.getStudents().stream()
                .map(studentDTO -> {
                    Student student = modelMapper.map(studentDTO, Student.class);
                    student.setTeacher(teacher); // Set the teacher for each student

                    List<Grade> grades = studentDTO.getGrades().stream()
                            .map(gradeDTO -> {
                                Grade grade = modelMapper.map(gradeDTO, Grade.class);
                                grade.setStudent(student); // Set the student for each grade
                                return grade;
                            })
                            .collect(Collectors.toList());

                    student.setGrades(grades);
                    return student;
                })
                .collect(Collectors.toList());

        teacher.setStudents(students);
        teacher.setUsername(teacherRequestDTO.getUsername());
        teacher.setPassword(teacherRequestDTO.getPassword());

        Teacher updatedTeacher = teacherRepository.save(teacher);
        TeacherResponseDTO teacherResponseDTO = modelMapper.map(updatedTeacher, TeacherResponseDTO.class);

        return teacherResponseDTO;
    }


    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        teacherRepository.deleteById(id);
    }

    @Override
    public List<StudentResponseDTO> getStudentsByTeacher(Long teacherId) {
        List<Student> studentsByTeacherId = teacherRepository.findStudentsByTeacherId(teacherId);
        List<StudentResponseDTO> studentResponseDTOS = studentsByTeacherId.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
        return studentResponseDTOS;
    }

    @Override
    public List<GradeResponseDTO> getGradesByTeacher(Long teacherId) {
        List<Grade> gradesByTeacherId = teacherRepository.findGradesByTeacherId(teacherId);
        List<GradeResponseDTO> gradeResponseDTOS = gradesByTeacherId.stream()
                .map(grade -> modelMapper.map(grade, GradeResponseDTO.class))
                .collect(Collectors.toList());
        return gradeResponseDTOS;
    }
}
