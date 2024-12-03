package ru.qq.mainapi.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.qq.mainapi.db.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT * FROM Course c WHERE :studentId = ANY(c.student_ids)", nativeQuery = true)
    List<Course> findAllByStudentId(@Param("studentId") Integer studentId);
}
