package ru.qq.mainapi.models.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto {

    @NotNull
    @Schema(description = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull
    @Schema(description = "surname", requiredMode = Schema.RequiredMode.REQUIRED)
    private String surname;

    @NotNull
    @Schema(description = "username", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
}
