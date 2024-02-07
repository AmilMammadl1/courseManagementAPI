package net.javaguides.todo.controller;

import net.javaguides.todo.dto.request.StudentRequestDTO;
import net.javaguides.todo.dto.request.TeacherRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;
import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.dto.response.TeacherResponseDTO;
import net.javaguides.todo.service.StudentService;
import net.javaguides.todo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/api/teacher/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacher(@PathVariable("id") Long teacherId) {
        TeacherResponseDTO teacherResponseDTO = teacherService.getTeacher(teacherId);
        return new ResponseEntity<>(teacherResponseDTO, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @GetMapping("api/teacher")
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        List<TeacherResponseDTO> allTeachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(allTeachers, HttpStatus.OK);
    }
    @GetMapping("/{teacherId}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByTeacher(@PathVariable("teacherId") Long teacherId) {
        List<StudentResponseDTO> studentResponseDTOS = teacherService.getStudentsByTeacher(teacherId);
        return new ResponseEntity<>(studentResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{teacherId}/grades")
    public ResponseEntity<List<GradeResponseDTO>> getGradesByTeacher(@PathVariable("teacherId") Long teacherId) {
        List<GradeResponseDTO> gradeResponseDTOList = teacherService.getGradesByTeacher(teacherId);
        return new ResponseEntity<>(gradeResponseDTOList, HttpStatus.OK);
    }

    @PostMapping("api/teacher")
    public ResponseEntity<TeacherResponseDTO> addTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO) {

        TeacherResponseDTO teacherResponseDTO = teacherService.addTeacher(teacherRequestDTO);

        return new ResponseEntity<>(teacherResponseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/api/teacher/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO, @PathVariable("id") Long studentId){
        TeacherResponseDTO teacherResponseDTO = teacherService.updateTeacher(teacherRequestDTO, studentId);
        return new ResponseEntity<>(teacherResponseDTO, HttpStatus.OK);
    }

    // Build Delete Todo REST API
    @DeleteMapping("/api/teacher/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>("Todo deleted successfully!.", HttpStatus.OK);
    }
}


