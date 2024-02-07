package net.javaguides.todo.service.impl;

import net.javaguides.todo.dto.request.TeacherRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.dto.response.TeacherResponseDTO;
import net.javaguides.todo.entity.Grade;
import net.javaguides.todo.entity.Student;
import net.javaguides.todo.entity.Teacher;
import net.javaguides.todo.exception.ResourceNotFoundException;
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
    private ModelMapper modelMapper;


    @Override
    public TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = modelMapper.map(teacherRequestDTO, Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);
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
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        List<Grade> grades = teacherRequestDTO.getGrades().stream()
                .map(gradeDTO -> modelMapper.map(gradeDTO, Grade.class))
                .collect(Collectors.toList());

        teacher.setGrades(grades);

        List<Student> students = teacherRequestDTO.getStudents().stream()
                .map(studentDTO -> modelMapper.map(studentDTO, Student.class))
                .collect(Collectors.toList());

        teacher.setStudents(students);

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
        List<Grade> gradesByTeacherId = teacherRepository.findGradesByStudentsTeacherId(teacherId);
        List<GradeResponseDTO> gradeResponseDTOList = gradesByTeacherId.stream()
                .map(grade -> modelMapper.map(grade, GradeResponseDTO.class))
                .collect(Collectors.toList());
        return gradeResponseDTOList;
    }
}
