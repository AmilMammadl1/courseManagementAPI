package net.javaguides.todo.controller;

import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.dto.request.GradeRequestDTO;
import net.javaguides.todo.dto.response.GradeResponseDTO;
import net.javaguides.todo.entity.Grade;
import net.javaguides.todo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/api/grade/{id}")
    public ResponseEntity<GradeResponseDTO> getGrade(@PathVariable("id") Long gradeId) {
        GradeResponseDTO gradeResponseDTO = gradeService.getGrade(gradeId);
        return new ResponseEntity<>(gradeResponseDTO, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @GetMapping("api/grade")
    public ResponseEntity<List<GradeResponseDTO>> getAllGrades() {
        List<GradeResponseDTO> allGrades = gradeService.getAllGrades();
        return new ResponseEntity<>(allGrades, HttpStatus.OK);
    }

    @PostMapping("api/grade")
    public ResponseEntity<GradeResponseDTO> addGrade(@RequestBody GradeRequestDTO gradeRequestDTO) {

        GradeResponseDTO gradeResponseDTO = gradeService.addGrade(gradeRequestDTO);

        return new ResponseEntity<>(gradeResponseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/api/grade/{id}")
    public ResponseEntity<GradeResponseDTO> updateGrade(@RequestBody GradeRequestDTO gradeRequestDTO, @PathVariable("id") Long gradeId){
        GradeResponseDTO gradeResponseDTO = gradeService.updateGrade(gradeRequestDTO, gradeId);
        return new ResponseEntity<>(gradeResponseDTO, HttpStatus.OK);
    }

    // Build Delete Todo REST API
    @DeleteMapping("/api/grade/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long gradeId){
        gradeService.deleteGrade(gradeId);
        return new ResponseEntity<>("Todo deleted successfully!.", HttpStatus.OK);
    }
    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<GradeResponseDTO>> getGradesByStudent(@PathVariable("studentId") Long studentId) {
        List<GradeResponseDTO> gradesResponseDTOByStudentId = gradeService.getGradesByStudent(studentId);
        return new ResponseEntity<>(gradesResponseDTOByStudentId, HttpStatus.OK);
    }

    @GetMapping("/by-subject/{subject}")
    public ResponseEntity<List<GradeResponseDTO>> getGradesBySubject(@PathVariable("subject") String subject) {
        List<GradeResponseDTO> gradesResponseDTOBySubject = gradeService.getGradesBySubject(subject);
        return new ResponseEntity<>(gradesResponseDTOBySubject, HttpStatus.OK);
    }
}
