package ru.qq.mainapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.qq.mainapi.db.entity.Course;
import ru.qq.mainapi.db.entity.Student;
import ru.qq.mainapi.db.entity.Teacher;
import ru.qq.mainapi.db.repository.CourseRepository;
import ru.qq.mainapi.db.repository.StudentRepository;
import ru.qq.mainapi.db.repository.TeacherRepository;
import ru.qq.mainapi.models.input.StudentDto;
import ru.qq.mainapi.models.input.TeacherDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public Teacher createTeacher(TeacherDto dto) {
        return teacherRepository.save(
                Teacher.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .username(dto.getUsername())
                        .build()
        );
    }

    public Student createStudent(StudentDto dto) {
        return studentRepository.save(
                Student.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .username(dto.getUsername())
                        .build()
        );
    }

    public Optional<Teacher> getTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    public void deleteTeacherById(Integer id) {
        teacherRepository.deleteById(id);
    }

    public List<Course> getAllCoursesByStudent(Integer id) {
        return courseRepository.findAllByStudentId(id);
    }
}
