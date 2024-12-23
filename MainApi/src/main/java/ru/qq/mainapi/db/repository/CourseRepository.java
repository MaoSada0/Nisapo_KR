package ru.qq.mainapi.db.repository;


import ru.qq.mainapi.db.entity.Course;

import java.util.List;
import java.util.Optional;

//@Repository
public interface CourseRepository /* extends JpaRepository<Course, Integer> */{
    Course save(Course build);

    Optional<Course> findById(Integer id);

    List<Course> findAll();

    void deleteById(Integer id);

    List<Course> findAllByUserId(Integer id);
//    @Query(value = "SELECT * FROM Course c WHERE :userId = ANY(c.user_ids)", nativeQuery = true)
//    List<Course> findAllByUserId(@Param("userId") Integer userId);
}
