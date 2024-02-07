package net.javaguides.todo.repository;

import net.javaguides.todo.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query("SELECT grade FROM Grade grade WHERE grade.student.id = :studentId")
    List<Grade> getGradesByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT g FROM Grade g WHERE g.subject = :subject")
    List<Grade> getGradesBySubject(@Param("subject") String subject);
}

