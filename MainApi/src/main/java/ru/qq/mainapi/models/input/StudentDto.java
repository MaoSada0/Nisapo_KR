package ru.qq.mainapi.models.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class StudentDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String username;
}
