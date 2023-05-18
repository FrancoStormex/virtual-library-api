package ar.com.stormex.app.library.common;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<D, E, C, U> {

    D toDto(E entity);

    List<D> toListDto(List<E> entityList);

    E toEntity(D dto);

    List<E> toDtoListToEntityList(List<D> dtoList);

    E fromCreateDtoToEntity(C create);

    E merge(@MappingTarget E target, U source);
}
