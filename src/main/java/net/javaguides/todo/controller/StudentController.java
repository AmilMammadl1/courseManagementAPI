package net.javaguides.todo.controller;

import net.javaguides.todo.dto.request.GradeRequestDTO;
import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.service.GradeService;
import net.javaguides.todo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/api/student/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable("id") Long studentId) {
        StudentResponseDTO studentResponseDTO = studentService.getStudent(studentId);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @GetMapping("api/student")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> allStudents = studentService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByTeacher(@PathVariable("teacherId") Long teacherId) {
        List<StudentResponseDTO> studentsResponseDTOByTeacherId = studentService.getStudentsByTeacher(teacherId);
        return new ResponseEntity<>(studentsResponseDTOByTeacherId, HttpStatus.OK);
    }

    @GetMapping("/with-grade-above/{grade}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsWithGradeAbove(@PathVariable("grade") Double grade) {
        List<StudentResponseDTO> studentsResponseDTOWithGradeAbove = studentService.getStudentsWithGradeAbove(grade);
        return new ResponseEntity<>(studentsResponseDTOWithGradeAbove, HttpStatus.OK);
    }

    @PostMapping("api/student")
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO studentResponseDTO = studentService.addStudent(studentRequestDTO);

        return new ResponseEntity<>(studentResponseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/api/student/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable("id") Long studentId){
        StudentResponseDTO studentResponseDTO = studentService.updateStudent(studentRequestDTO, studentId);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
    }

    // Build Delete Todo REST API
    @DeleteMapping("/api/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student deleted successfully!.", HttpStatus.OK);
    }
}
