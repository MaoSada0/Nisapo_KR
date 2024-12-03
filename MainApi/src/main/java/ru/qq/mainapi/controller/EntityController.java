package ru.qq.mainapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.mainapi.models.input.StudentDto;
import ru.qq.mainapi.models.input.TeacherDto;
import ru.qq.mainapi.service.EntityService;

@RestController
@RequiredArgsConstructor
public class EntityController {

    private final EntityService entityService;

    @PutMapping("platform/teacher")
    public ResponseEntity<?> createTeacher(@RequestBody @Valid TeacherDto dto){
        return ResponseEntity.ok().body(entityService.createTeacher(dto));
    }

    @PutMapping("platform/student")
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentDto dto){
        return ResponseEntity.ok().body(entityService.createStudent(dto));
    }

    @GetMapping("platform/teacher")
    public ResponseEntity<?> getTeacherById(@RequestParam("id") Integer id){
        var teacher = entityService.getTeacherById(id);

        if (teacher.isPresent()) {
            return ResponseEntity.ok().body(teacher.get());
        }

        return ResponseEntity.badRequest()
                .body(String.format("No teacher with such id: %d", id));
    }

    @GetMapping("platform/student")
    public ResponseEntity<?> getStudentById(@RequestParam("id") Integer id){
        var student = entityService.getStudentById(id);

        if (student.isPresent()) {
            return ResponseEntity.ok().body(student.get());
        }

        return ResponseEntity.badRequest()
                .body(String.format("No student with such id: %d", id));
    }

    @GetMapping("platform/student/all")
    public ResponseEntity<?> getAllStudents(){
        return ResponseEntity.ok().body(entityService.getAllStudents());
    }


    @GetMapping("platform/teacher/all")
    public ResponseEntity<?> getAllTeachers(){
        return ResponseEntity.ok().body(entityService.getAllTeachers());
    }

    @DeleteMapping("platform/teacher")
    public ResponseEntity<?> deleteTeacherById(@RequestParam("id") Integer id){
        entityService.deleteStudentById(id);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("platform/student")
    public ResponseEntity<?> deleteStudentById(@RequestParam("id") Integer id){
        entityService.deleteTeacherById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("platform/student/courses")
    public ResponseEntity<?> getAllCoursesThatHaveStudent(@RequestParam("id") Integer id) {
        return ResponseEntity.ok().body(entityService.getAllCoursesByStudent(id));
    }
}
