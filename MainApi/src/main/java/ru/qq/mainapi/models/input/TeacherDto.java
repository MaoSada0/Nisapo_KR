package ru.qq.mainapi.models.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class TeacherDto {

    @NotNull
    private Integer id;

    private List<Integer> courseIds;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String username;
}
