package ru.qq.mainapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.qq.mainapi.db.entity.Course;
import ru.qq.mainapi.db.entity.User;
import ru.qq.mainapi.db.repository.CourseRepository;
import ru.qq.mainapi.db.repository.UserRepository;
import ru.qq.mainapi.models.input.UserDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public User createUser(UserDto dto) {
        return userRepository.save(
                User.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .username(dto.getUsername())
                        .build()
        );
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Course> getAllCoursesByUserId(Integer id) {
        return courseRepository.findAllByUserId(id);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
