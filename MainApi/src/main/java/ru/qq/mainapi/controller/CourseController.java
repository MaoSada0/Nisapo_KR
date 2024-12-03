package ru.qq.mainapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.mainapi.models.input.CourseDto;
import ru.qq.mainapi.service.CourseService;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PutMapping("platform/course")
    public ResponseEntity<?> createCourse(@RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok().body(courseService.createCourse(dto));
    }

    @GetMapping("platform/course")
    public ResponseEntity<?> getCourseById(@RequestParam("id") Integer id) {
        var course = courseService.getCourseById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok().body(course.get());
        }

        return ResponseEntity.badRequest()
                .body(String.format("No course with such id: %d", id));
    }

    @GetMapping("platform/course/all")
    public ResponseEntity<?> getAllCourses() {
       return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @DeleteMapping("platform/course")
    public ResponseEntity<?> deleteCourseById(@RequestParam("id") Integer id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("platform/course/student")
    public ResponseEntity<?> addStudentToCourse(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentId") Integer studentId
    ) {
        return ResponseEntity.ok().body(courseService.addStudentToCourse(courseId, studentId));
    }

    @DeleteMapping("platform/course/student")
    public ResponseEntity<?> deleteStudentFromCourse(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentId") Integer studentId
    ) {
        courseService.deleteStudentFromCourse(courseId, studentId);

        return ResponseEntity.ok().build();
    }

}
