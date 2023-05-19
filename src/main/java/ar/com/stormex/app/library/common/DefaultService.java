package ar.com.stormex.app.library.common;

import ar.com.stormex.app.library.model.entity.AuthorEntity;
import ar.com.stormex.app.library.response.AbstractResponse;
import ar.com.stormex.app.library.response.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public abstract class DefaultService<D, E, C, U, T> extends AbstractResponse {

    protected abstract JpaRepository<E, T> getRepository();

    protected abstract BaseMapper<D, E, C, U> getMapper();

    protected abstract String getName();

    protected abstract boolean exists(C create);

    protected abstract boolean sameValues(U update, E entity);

    protected abstract boolean sameValuesExistToUpdateInAnotherItem(U update);

    public ResponseObject<D> create(C create) {
        if(exists(create)) {
            return super.errorResponse(getName() + " it was not created because it already exists", HttpStatus.CONFLICT.value());
        }
        E entity = getMapper().fromCreateDtoToEntity(create);
        entity = getRepository().save(entity);
        D dto = getMapper().toDto(entity);

        return super.okResponse(dto, getName() + " created successfully", HttpStatus.CREATED.value());
    }

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

    public ResponseObject<D> findById(T id) {
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()){
            return super.errorResponse(getName() + " does not found", HttpStatus.NOT_FOUND.value());
        }
        E entity = optional.get();
        D dto = getMapper().toDto(entity);
        return super.okResponse(dto, getName() + " found", HttpStatus.OK.value());
    }

    public ResponseObject<String> delete(T id) {
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()){
            return super.errorResponse(getName() + " does not found", HttpStatus.NOT_FOUND.value());
        }
        getRepository().deleteById(id);
        return super.okResponse(null, getName() + " deleted", HttpStatus.NO_CONTENT.value());
    }

    public ResponseObject<List<D>> findAll() {
        List<E> entities = getRepository().findAll();
        if(entities.isEmpty()){
            return super.errorResponse(getName() + "s not found", HttpStatus.NOT_FOUND.value());
        }
        List<D> dtoList = getMapper().toListDto(entities);
        return super.okResponse(dtoList, getName() + "s found", HttpStatus.OK.value());
    }
}
