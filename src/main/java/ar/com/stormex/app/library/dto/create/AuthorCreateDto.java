package ar.com.stormex.app.library.dto.create;

import lombok.Data;

import java.time.LocalDate;

/**
 * ES:
 * DTO para la creación de los datos del autor.
 *
 * EN:
 * DTO for creating the author's data.
 */
@Data
public class AuthorCreateDto {

    private LocalDate birthDate;

    private String gender;

    private String name;

    private String nationality;

    private String profession;
}
