package ar.com.stormex.app.library.common;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Interface that implements the base methods for each Mapper class. / Interfaz que implementa los métodos base para cada clase Mapper.
 *
 * @param <D> General DTO. / DTO general.
 * @param <E> Entity. / Entidad.
 * @param <C> Create DTO. / DTO de creación.
 * @param <U> Update DTO. / DTO de actualización.
 */
public interface BaseMapper<D, E, C, U> {

    /**
     * Maps the attributes of an Entity to the indicated DTO. / Mapea los atributos de una Entidad hacia el DTO indicado.
     *
     * @param entity Entity received as a parameter. / Entidad recibida como parámetro.
     * @return The DTO with the attributes mapped. / El DTO con los atributos mapeados.
     */
    D toDto(E entity);

    /**
     * Maps the attributes of a list of Entities received as a parameter, to a list of the indicated DTOs.
     *
     * @param entityList List of Entities received as a parameter. / Lista de Entidades recibida como parámetro.
     * @return List of DTOs with their mapped attributes. / Lista de DTOs con sus atributos mapeados.
     */
    List<D> toListDto(List<E> entityList);

    /**
     * Maps the attributes of an DTO to the indicated Entity. / Mapea los atributos de un DTO hacia la Entidad indicada.
     *
     * @param dto DTO received as a parameter. / DTO recibido como parámetro.
     * @return The Entity with the attributes mapped. / La Entidad con los atributos mapeados.
     */
    E toEntity(D dto);

    /**
     * Maps the attributes of an Create DTO to the indicated Entity. / Mapea los atributos de un DTO de creación hacia la Entidad indicada.
     *
     * @param create Create DTO received as a parameter. / DTO de creación recibido como parámetro.
     * @return The Entity with the attributes mapped. / La Entidad con los atributos mapeados.
     */
    E fromCreateDtoToEntity(C create);

    /**
     * Performs a merge of the attributes that the update DTO has towards the attributes that the Entity has. / Realiza una fusión de los atributos que tiene el DTO de actualización hacia los atributos que tiene la Entidad.
     *
     * @param entity Entity that receives an attribute update through the merge. / Entidad que recibe una actualización de atributos mediante la fusión.
     * @param update Update DTO that has the new attributes to be mapped to the entity. / DTO de actualización que tiene los nuevos atributos que se asignarán a la entidad.
     * @return The entity with the merged attributes. / La entidad con los atributos fusionados.
     */
    E merge(@MappingTarget E entity, U update);
}
