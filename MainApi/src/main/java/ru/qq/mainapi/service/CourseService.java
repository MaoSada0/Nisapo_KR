package ru.qq.mainapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.qq.mainapi.db.entity.Course;
import ru.qq.mainapi.db.repository.CourseRepository;
import ru.qq.mainapi.error.BadRequestException;
import ru.qq.mainapi.models.input.CourseDto;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final EntityService entityService;

    public Course createCourse(CourseDto dto) {
        return courseRepository.save(
                Course.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .price(Optional.ofNullable(dto.getPrice()).orElse(0))
                        .creatorId(dto.getCourseCreatorId())
                        .userIds(new HashSet<>())
                        .build()
        );
    }

    public Optional<Course> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

    public Course addUserToCourse(Integer courseId, Integer userId) {
        if (entityService.getUserById(userId).isEmpty()) {
            throw new BadRequestException(String.format("No student with such id: %d", userId));
        }

        var course = getCourseById(courseId);

        if (course.isEmpty()) {
            throw new BadRequestException(String.format("No course with such id: %d", courseId));
        }

        course.get().getUserIds().add(userId);

        return courseRepository.save(course.get());
    }

    public void deleteStudentFromCourse(Integer courseId, Integer studentId) {
        if (entityService.getUserById(studentId).isEmpty()) {
            throw new BadRequestException(String.format("No student with such id: %d", studentId));
        }

        var course = getCourseById(courseId);

        if (course.isEmpty()) {
            throw new BadRequestException(String.format("No course with such id: %d", courseId));
        }

        course.get().getUserIds().remove(studentId);
    }
}
