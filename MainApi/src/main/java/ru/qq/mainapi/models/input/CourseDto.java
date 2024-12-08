package ru.qq.mainapi.models.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CourseDto {

    @NotNull
    @Schema(description = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull
    @Schema(description = "description", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(description = "price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer price;

    @NotNull
    @Schema(description = "courseCreatorId", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer courseCreatorId;
}
