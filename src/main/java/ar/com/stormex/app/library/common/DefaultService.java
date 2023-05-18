package ar.com.stormex.app.library.common;

import ar.com.stormex.app.library.response.AbstractResponse;
import ar.com.stormex.app.library.response.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public abstract class DefaultService<D, E, C, U, T> extends AbstractResponse {

    protected abstract JpaRepository<E, T> getRepository();

    protected abstract BaseMapper<D, E, C, U> getMapper();

    protected abstract boolean exists(C create);

    protected abstract boolean sameValues(U update, E entity);

    protected abstract boolean sameValuesExistToUpdateInAnotherItem(U update);

    public ResponseObject<D> create(C create, String message) {
        E entity = getMapper().fromCreateDtoToEntity(create);
        entity = getRepository().save(entity);
        D dto = getMapper().toDto(entity);

        return super.okResponse(dto, message, HttpStatus.CREATED.value());
    }

    public ResponseObject<D> update(U update, String message) {
        T id = ((BaseObject<T>) update).getId();
        E entity = getRepository().getReferenceById(id);
        if (!sameValues(update, entity)) {
            if (sameValuesExistToUpdateInAnotherItem(update)) {
                return super.errorResponse("The element could not be updated because another element exists with the same values", HttpStatus.CONFLICT.value());
            }
            entity = getMapper().merge(entity, update);
            entity = getRepository().save(entity);
        }
        D dto = getMapper().toDto(entity);

        return super.okResponse(dto, message, HttpStatus.OK.value());
    }
}
