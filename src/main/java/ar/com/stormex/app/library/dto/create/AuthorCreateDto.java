package ar.com.stormex.app.library.dto.create;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for creating the author's data. / DTO para la creación de los datos del autor.
 */
@Data
public class AuthorCreateDto {

    private LocalDate birthDate;

    private String gender;

    private String name;

    private String nationality;

    private String profession;
}
