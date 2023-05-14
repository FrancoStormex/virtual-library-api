package ar.com.stormex.app.library.mapper;

import ar.com.stormex.app.library.dto.AuthorDto;
import ar.com.stormex.app.library.dto.create.AuthorCreateDto;
import ar.com.stormex.app.library.dto.update.AuthorUpdateDto;
import ar.com.stormex.app.library.model.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * ES:
 * Mapeador de los autores.
 *
 * EN:
 * Mapper of the authors.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

    AuthorDto toDto(AuthorEntity authorEntity);

    List<AuthorDto> toDtoList(List<AuthorEntity> authorEntities);

    AuthorEntity toEntity(AuthorDto authorDto);

    List<AuthorEntity> toDtoListToEntityList(List<AuthorDto> authorDto);

    AuthorEntity fromCreateDtoToEntity(AuthorCreateDto authorCreateDto);

    AuthorEntity merge(@MappingTarget AuthorEntity target, AuthorUpdateDto source);
}
