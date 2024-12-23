package ru.qq.mainapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.mainapi.db.entity.Course;
import ru.qq.mainapi.models.input.CourseDto;
import ru.qq.mainapi.service.CourseService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Course Controller", description = "API для управления курсами")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Создать курс", description = "Создает новый курс на платформе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Курс успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseDto.class))),
            @ApiResponse(responseCode = "400", description = "Данные невалидны", content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера", content = @Content(schema = @Schema))
    })
    @PutMapping("platform/course")
    public ResponseEntity<?> createCourse(@RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok().body(courseService.createCourse(dto));
    }

    @Operation(summary = "Получить курс по ID", description = "Возвращает курс по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Курс найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "400", description = "Данные невалидны", content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера", content = @Content(schema = @Schema))
    })
    @GetMapping("platform/course")
    public ResponseEntity<?> getCourseById(@RequestParam("id") Integer id) {
        var course = courseService.getCourseById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok().body(course.get());
        }

        return ResponseEntity.badRequest()
                .body(String.format("No course with such id: %d", id));
    }

    @Operation(summary = "Получить все курсы", description = "Возвращает список всех курсов")
    @ApiResponse(responseCode = "200", description = "Список курсов успешно получен",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Course.class)))
    @GetMapping("platform/course/all")
    public ResponseEntity<?> getAllCourses() {
       return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @Operation(summary = "Удалить студента с курса", description = "Удаляет студента с указанного курса")
    @ApiResponse(responseCode = "200", description = "Студент успешно удален с курса")
    @DeleteMapping("platform/course")
    public ResponseEntity<?> deleteCourseById(@RequestParam("id") Integer id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавить студента на курс", description = "Добавляет студента к указанному курсу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Студент успешно добавлен на курс"),
            @ApiResponse(responseCode = "400", description = "Данные невалидны", content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера", content = @Content(schema = @Schema))
    })
    @PutMapping("platform/course/student")
    public ResponseEntity<?> addUserToCourse(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentId") Integer userId
    ) {
        return ResponseEntity.ok().body(courseService.addUserToCourse(courseId, userId));
    }

    @Operation(summary = "Удалить студента с курса", description = "удаляет студента с указанного курса")
    @ApiResponse(responseCode = "200", description = "Студент успешно удален с курса")
    @DeleteMapping("platform/course/student")
    public ResponseEntity<?> deleteStudentFromCourse(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentId") Integer studentId
    ) {
        courseService.deleteStudentFromCourse(courseId, studentId);

        return ResponseEntity.ok().build();
    }

}
