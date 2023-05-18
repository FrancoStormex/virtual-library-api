package ar.com.stormex.app.library.dto.update;

import ar.com.stormex.app.library.common.BaseObject;
import ar.com.stormex.app.library.dto.create.AuthorCreateDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for updating the author's data. / DTO para la actualización de los datos del autor.
 */
@Data
public class AuthorUpdateDto extends AuthorCreateDto implements BaseObject<Long> {

    @NotNull
    private Long id;
}
