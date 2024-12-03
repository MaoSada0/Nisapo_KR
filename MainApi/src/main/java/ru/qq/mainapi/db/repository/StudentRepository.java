package ru.qq.mainapi.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.qq.mainapi.db.entity.Course;
import ru.qq.mainapi.db.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


}
