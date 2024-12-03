package ru.qq.mainapi.models.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class CourseDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    private Integer price;

    @NotNull
    private Integer courseCreatorId;
}
