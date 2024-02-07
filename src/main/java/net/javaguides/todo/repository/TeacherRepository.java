package net.javaguides.todo.repository;

import net.javaguides.todo.entity.Grade;
import net.javaguides.todo.entity.Student;
import net.javaguides.todo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT s FROM Student s WHERE s.teacher.id = :teacherId")
    List<Student> findStudentsByTeacherId(@Param("teacherId") Long teacherId);
    @Query("SELECT g FROM Grade g JOIN g.student s WHERE s.teacher.id = :teacherId")
    List<Grade> findGradesByStudentsTeacherId(@Param("teacherId") Long teacherId);
}

