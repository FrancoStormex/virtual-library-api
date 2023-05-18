package ar.com.stormex.app.library.service;

import ar.com.stormex.app.library.common.DefaultService;
import ar.com.stormex.app.library.dto.AuthorDto;
import ar.com.stormex.app.library.dto.create.AuthorCreateDto;
import ar.com.stormex.app.library.dto.update.AuthorUpdateDto;
import ar.com.stormex.app.library.mapper.AuthorMapper;
import ar.com.stormex.app.library.model.entity.AuthorEntity;
import ar.com.stormex.app.library.repository.AuthorRepository;
import ar.com.stormex.app.library.response.ResponseObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service that encapsulates the logic for managing authors. / Servicio que encapsula la lógica para la gestión de los autores.
 */
@Data
@Service
public class AuthorService extends DefaultService<AuthorDto, AuthorEntity, AuthorCreateDto, AuthorUpdateDto, Long> {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    @Override
    public boolean exists(AuthorCreateDto create) {
        return repository.findByName(create.getName()).isPresent();
    }

    @Override
    protected boolean sameValues(AuthorUpdateDto update, AuthorEntity entity) {
        return update.getBirthDate().equals(entity.getBirthDate()) &&
                update.getGender().equals(entity.getGender()) &&
                update.getName().equals(entity.getName()) &&
                update.getNationality().equals(entity.getNationality()) &&
                update.getProfession().equals(entity.getProfession());
    }

    @Override
    protected boolean sameValuesExistToUpdateInAnotherItem(AuthorUpdateDto update) {
        return repository.findByIdNotAndName(update.getId(), update.getName()).isPresent();
    }

    public ResponseObject<AuthorDto> create(AuthorCreateDto create) {
        if (exists(create)) {
            return super.errorResponse("Author does not created", HttpStatus.CONFLICT.value());
        }
        return super.create(create, "Author created successfully");
    }

    public ResponseObject<AuthorDto> update(AuthorUpdateDto update) {
        Optional<AuthorEntity> optional = getRepository().findById(update.getId());

        if (optional.isEmpty()) {
            return super.errorResponse("Author does not updated", HttpStatus.CONFLICT.value());
        }
        return super.update(update, "Author updated successfully");
    }
//
//    public ResponseObject<AuthorDto> findById(Long id) {
//        Optional<AuthorEntity> optional = getRepository().findById(id);
//
//        if (optional.isEmpty()){
//            return super.errorResponse("Author does not found", HttpStatus.NOT_FOUND.value());
//        }
//        AuthorDto authorDto = getMapper().toDto(optional.get());
//
//        return super.okResponse(authorDto,"Author found",HttpStatus.OK.value());
//    }
//
//    public ResponseObject<String> delete(Long id){
//        Optional<AuthorEntity> optional = getRepository().findById(id);
//
//        if (optional.isEmpty()){
//            return super.errorResponse("Author does not found", HttpStatus.NOT_FOUND.value());
//        }
//        getRepository().deleteById(id);
//
//        return super.okResponse(null,"Author deleted",HttpStatus.NO_CONTENT.value());
//    }
//
//    public ResponseObject<List<AuthorDto>> findAll() {
//        List<AuthorEntity> authors = getRepository().findAll();
//        List<AuthorDto> authorDtoList = getMapper().toDtoList(authors);
//
//        return super.okResponse(authorDtoList,"Authors found",HttpStatus.OK.value());
//    }
}
