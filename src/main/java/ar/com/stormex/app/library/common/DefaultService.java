package ar.com.stormex.app.library.common;

import ar.com.stormex.app.library.response.AbstractResponse;
import ar.com.stormex.app.library.response.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Abstract class with the default implementation for each service. / Clase abstracta con la implementación por defecto para cada servicio.
 * There are functionalities in common between the different services (such as create, update, delete, find, etc) that are essential for the proper functioning of the application, which is why this abstraction is made. / Existen funcionalidades en común entre los diferentes servicios (tales como create, update, delete, find, etc) que son indispensables para el correcto funcionamiento de la aplicación, es por ello que se realiza esta abstracción.
 * It allows reuse of legacy code, modularity, greater modifiability and ease of detecting errors. / Permite reutilización de código heredado, modularidad, mayor modificabilidad y facilidad para detectar errores.
 *
 * @param <D> General DTO. / DTO general.
 * @param <E> Entity. / Entidad.
 * @param <C> Create DTO. / DTO de creación.
 * @param <U> Update DTO. / DTO de actualización.
 * @param <T> Generic type parameter. / Parámetro de tipo genérico.
 */
@Slf4j
@Service
public abstract class DefaultService<D, E, C, U, T> extends AbstractResponse {

    /**
     * Makes a call to the embedded repository in the service's dependency injection. / Realiza una llamada al repositorio incrustado en la inyección de dependencia del servicio.
     * It corresponds to a JPA interface and allows to perform a CRUD towards the repositories in a simplified way. / Corresponde a una interfaz JPA y permite realizar un CRUD hacia los repositorios de forma simplificada.
     * E corresponds to an entity and T to an identifier. / E corresponde a una entidad y T a un identificador.
     *
     * @return The repository corresponding to the one injected as a dependency. / El repositorio correspondiente al inyectado como dependencia.
     */
    protected abstract JpaRepository<E, T> getRepository();

    /**
     * Makes a call to the mapper embedded in the dependency injection of the service. / Realiza una llamada al mapper insertado en la inyección de dependencia del servicio.
     * It corresponds to a BaseMapper interface and allows the corresponding mappings to be made to the mapper. / Corresponde a una interfaz de BaseMapper y permite realizar los mapeos correspondientes al mapper.
     *
     * @return The Mapper corresponding to the one injected as a dependency. / El Mapper correspondiente al inyectado como dependencia.
     */
    protected abstract BaseMapper<D, E, C, U> getMapper();

    /**
     * Gets the name implemented in the contract of the subclass that implements it. / Obtiene el nombre implementado en el contrato de la subclase que lo implementa.
     *
     * @return The name that implements the subclass. / El nombre que implementa la subclase.
     */
    protected abstract String getName();

    /**
     * Performs a comparison between all the attributes (except id) of a creation DTO received as a parameter and the attributes of the entities located in the repository. / Realiza una comparación entre todos los atributos (excepto id) de un DTO de creación recibido como parámetro y los atributos de las entidades ubicadas en el repositorio.
     *
     * @param create Creation DTO received by parameter. / DTO de creación recibido por parámetro.
     * @return A boolean true if an entity already exists in the repository with the same attributes, or false if it does not exist. / Un booleano verdadero si ya existe una entidad en el repositorio con los mismos atributos o falso en caso de que no exista.
     */
    protected abstract boolean exists(C create);

    /**
     * It performs a comparison between all the attributes of an update DTO received as a parameter with the Entity located in the repository, which it obtains through the id attribute that said DTO includes. / Realiza una comparación entre todos los atributos de un DTO de actualización recibido como parámetro con la Entidad ubicada en el repositorio, la cual obtiene mediante el atributo id que incluye dicho DTO.
     *
     * @param update An update DTO. / Un DTO de actualizacion.
     * @param entity An Entity. / Una entidad.
     * @return A boolean true if both the update DTO and the Entity are the same, or false if they are different. / Un booleano verdadero si tanto el DTO de actualización como la  Entidad son iguales o falso en caso de ser diferentes.
     */
    protected abstract boolean sameValues(U update, E entity);

    /**
     * It performs a comparison between some relevant attributes, specified from an update DTO received as a parameter, with the entities located in the repository, as a particularity it ignores that the id can be the same or different. / Realiza una comparación entre algunos atributos relevantes, especificados de un DTO de actualización recibido como parámetro con las entidades ubicadas en el repositorio, como particularidad ignora que el id pueda ser igual o diferente.
     *
     * @param update An update DTO. / Un DTO de actualizacion.
     * @return A boolean true if an entity already exists in the repository with the same relevant attributes, or false if it does not exist. / Un booleano verdadero si ya existe una entidad en el repositorio con los mismos atributos relevantes o falso en caso de que no exista.
     */
    protected abstract boolean sameValuesExistToUpdateInAnotherItem(U update);

    /**
     * It allows to create an entity and store it in the Repository from a DTO of creation. / Permite crear una entidad y almacenarla en el Repositorio desde un DTO de creación.
     * It requires that both the Mapper and the service Repository be injected as a dependency. / Requiere que tanto el Mapeador como el Repositorio de servicios se inyecten como una dependencia.
     *
     * @param create Creation DTO received by parameter. / DTO de creación recibido por parámetro.
     * @return A DTO type Response Object with complete information. / Un Objeto de Respuesta de tipo DTO con la información completa.
     */
    public ResponseObject<D> create(C create) {
        if(exists(create)) {
            return super.errorResponse(getName() + " it was not created because it already exists", HttpStatus.CONFLICT.value());
        }
        E entity = getMapper().fromCreateDtoToEntity(create);
        entity = getRepository().save(entity);
        D dto = getMapper().toDto(entity);

        return super.okResponse(dto, getName() + " created successfully", HttpStatus.CREATED.value());
    }

    /**
     * It allows updating an Entity and storing it in the Repository from an update DTO. / Permite actualizar una Entidad y almacenarla en el Repositorio a partir de un DTO de actualización.
     * It requires that both the Mapper and the service Repository be injected as a dependency. / Requiere que tanto el Mapeador como el Repositorio de servicios se inyecten como una dependencia.
     *
     * @param update An update DTO. / Un DTO de actualizacion.
     * @return A DTO type Response Object with complete information. / Un Objeto de Respuesta de tipo DTO con la información completa.
     */
    public ResponseObject<D> update(U update) {
        T id = ((BaseObject<T>) update).getId();
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()) {
            return super.errorResponse(getName() + " does not found", HttpStatus.NOT_FOUND.value());
        }
        E entity = getRepository().getReferenceById(id);
        if (!sameValues(update, entity)) {
            if (sameValuesExistToUpdateInAnotherItem(update)) {
                return super.errorResponse("The " + getName() + " could not be updated because another " + getName().toLowerCase(Locale.ROOT) + " exists with the same values", HttpStatus.CONFLICT.value());
            }
            entity = getMapper().merge(entity, update);
            entity = getRepository().save(entity);
        }
        D dto = getMapper().toDto(entity);
        return super.okResponse(dto, getName() + " updated succesfully", HttpStatus.OK.value());
    }

    /**
     * Allows you to found an entity stored in the repository by entering an ID. / Permite realizar la búsqueda de una entidad almacenada en el repositorio mediante el ingreso de un ID.
     *
     * @param id A ID received as a parameter. / Un ID recibido como parámetro.
     * @return A Response Object of type DTO with the complete information, Mapped from the Entity obtained from the Repository. / Un objeto de respuesta de tipo DTO con la información completa, Mapeada desde la Entidad obtenida del Repositorio.
     */
    public ResponseObject<D> findById(T id) {
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()){
            return super.errorResponse(getName() + " does not found", HttpStatus.NOT_FOUND.value());
        }
        E entity = optional.get();
        D dto = getMapper().toDto(entity);
        return super.okResponse(dto, getName() + " found", HttpStatus.OK.value());
    }

    /**
     * Allows you to delete an entity stored in the repository by entering an ID. / Permite realizar el borrado de una entidad almacenada en el repositorio mediante el ingreso de un ID.
     *
     * @param id A ID received as a parameter. / Un ID recibido como parámetro.
     * @return A Response Object of type String, which returns the successful deletion message or an error message in case the entity is not found. / Un Objeto de Respuesta de tipo String, que devuelve el mensaje de eliminación correcta o un mensaje de error en caso de que no se encuentre la entidad.
     */
    public ResponseObject<String> delete(T id) {
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()){
            return super.errorResponse(getName() + " does not found", HttpStatus.NOT_FOUND.value());
        }
        getRepository().deleteById(id);
        return super.okResponse(null, getName() + " deleted", HttpStatus.NO_CONTENT.value());
    }

    /**
     * Returns a list with DTOs Mapped from the Entities returned by the Repository. / Devuelve una lista con DTO Mapeados de las Entidades devueltas por el Repositorio.
     *
     * @return A response object with a list of type DTO. / Un objeto de respuesta con una lista de tipo DTO.
     */
    public ResponseObject<List<D>> findAll() {
        List<E> entities = getRepository().findAll();
        if(entities.isEmpty()){
            return super.errorResponse(getName() + "s not found", HttpStatus.NOT_FOUND.value());
        }
        List<D> dtoList = getMapper().toListDto(entities);
        return super.okResponse(dtoList, getName() + "s found", HttpStatus.OK.value());
    }
}
