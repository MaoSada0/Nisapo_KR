package ru.qq.mainapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.mainapi.models.input.UserDto;
import ru.qq.mainapi.service.EntityService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EntityController {

    private final EntityService entityService;

    @PutMapping("platform/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto dto){
        return ResponseEntity.ok().body(entityService.createUser(dto));
    }

    @GetMapping("platform/user")
    public ResponseEntity<?> getUserById(@RequestParam("id") Integer id){
        var user = entityService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        }

        return ResponseEntity.badRequest()
                .body(String.format("No student with such id: %d", id));
    }

    @GetMapping("platform/user/all")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok().body(entityService.getAllUsers());
    }


    @DeleteMapping("platform/user")
    public ResponseEntity<?> deleteUserById(@RequestParam("id") Integer id){
        entityService.deleteUserById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("platform/user/courses")
    public ResponseEntity<?> getAllCoursesThatHaveUser(@RequestParam("id") Integer id) {
        return ResponseEntity.ok().body(entityService.getAllCoursesByUserId(id));
    }

    @GetMapping("platform/user/password")
    public ResponseEntity<?> getPasswordByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok().build();
    }
}
