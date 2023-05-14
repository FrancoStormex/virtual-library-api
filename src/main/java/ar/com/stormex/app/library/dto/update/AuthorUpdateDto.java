package ar.com.stormex.app.library.dto.update;

import ar.com.stormex.app.library.dto.create.AuthorCreateDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ES:
 * DTO para la actualización de los datos del autor.
 *
 * EN:
 * DTO for updating the author's data.
 */
@Data
public class AuthorUpdateDto extends AuthorCreateDto {

    @NotNull
    private Long id;
}
