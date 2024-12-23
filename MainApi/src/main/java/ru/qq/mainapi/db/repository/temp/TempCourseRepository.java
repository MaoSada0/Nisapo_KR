package ru.qq.mainapi.db.repository.temp;

import org.springframework.stereotype.Component;
import ru.qq.mainapi.db.entity.Course;
import ru.qq.mainapi.db.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TempCourseRepository implements CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    @Override
    public Course save(Course build) {
        courses.add(build);

        return build;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public void deleteById(Integer id) {
        var crs = courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (crs.isPresent()) {
           courses.remove(crs);
        }
    }

    @Override
    public List<Course> findAllByUserId(Integer id) {
        return courses.stream()
                .filter(c -> c.getUserIds().contains(id))
                .toList();
    }
}
