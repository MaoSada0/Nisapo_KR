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
import ru.qq.mainapi.db.entity.User;
import ru.qq.mainapi.models.input.UserDto;
import ru.qq.mainapi.service.EntityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Entity Controller", description = "API для управления пользователями")
public class EntityController {

    private final EntityService entityService;

    @PutMapping("platform/user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Данные невалидны", content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера", content = @Content(schema = @Schema))
    })
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto dto){
        return ResponseEntity.ok().body(entityService.createUser(dto));
    }


    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Данные невалидны", content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера", content = @Content(schema = @Schema))
    })
    @GetMapping("platform/user")
    public ResponseEntity<?> getUserById(@RequestParam("id") Integer id){
        var user = entityService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        }

        return ResponseEntity.badRequest()
                .body(String.format("No student with such id: %d", id));
    }

    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей платформы")
    @ApiResponse(
            responseCode = "200",
            description = "Список пользователей успешно получен",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))
    )
    @GetMapping("platform/user/all")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok().body(entityService.getAllUsers());
    }

    @Operation(summary = "Удалить пользователя по ID", description = "Удаляет пользователя по указанному идентификатору")
    @ApiResponse(responseCode = "200", description = "Пользователь успешно удален")
    @DeleteMapping("platform/user")
    public ResponseEntity<?> deleteUserById(@RequestParam("id") Integer id){
        entityService.deleteUserById(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить курсы пользователя", description = "Возвращает список всех курсов, на которых зарегистрирован пользователь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список курсов успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "400", description = "Данные невалидны", content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера", content = @Content(schema = @Schema))
    })
    @GetMapping("platform/user/courses")
    public ResponseEntity<?> getAllCoursesThatHaveUser(@RequestParam("id") Integer id) {
        return ResponseEntity.ok().body(entityService.getAllCoursesByUserId(id));
    }

    @Operation(summary = "Получить пароль по имени пользователя", description = "Возвращает пароль по имени пользователя")
    @ApiResponse(responseCode = "200", description = "Пароль успешно получен")
    @GetMapping("platform/user/password")
    public ResponseEntity<?> getPasswordByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok().build();
    }
}
