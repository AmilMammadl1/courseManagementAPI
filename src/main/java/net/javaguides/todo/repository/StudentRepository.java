package net.javaguides.todo.repository;

import net.javaguides.todo.dto.response.StudentResponseDTO;
import net.javaguides.todo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<StudentResponseDTO> findByTeacherId(Long teacherId);

    @Query("SELECT s FROM Student s WHERE s.grade.grade > :grade")
    List<Student> getStudentsWithGradeAbove(@Param("grade") Double grade);

}
