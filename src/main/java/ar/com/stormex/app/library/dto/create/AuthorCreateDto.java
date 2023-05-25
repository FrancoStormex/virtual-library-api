package ar.com.stormex.app.library.dto.create;

import ar.com.stormex.app.library.model.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for creating the author's data. / DTO para la creación de los datos del autor.
 */
@Schema(description = "Class for create an author in the application")
@Data
public class AuthorCreateDto {

    @Schema(title = "Birth Date", example = "1997-03-29")
    private LocalDate birthDate;

    @Schema(title = "Gender", example = "MALE")
    private GenderEnum gender;

    @Schema(title = "Name", example = "Franco Martinez")
    private String name;

    @Schema(title = "Nationality", example = "Argentina")
    private String nationality;

    @Schema(title = "Profession or occupation", example = "Programmer")
    private String profession;
}
