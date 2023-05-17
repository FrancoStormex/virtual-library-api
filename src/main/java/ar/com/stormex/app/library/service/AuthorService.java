package ar.com.stormex.app.library.service;

import ar.com.stormex.app.library.dto.AuthorDto;
import ar.com.stormex.app.library.dto.create.AuthorCreateDto;
import ar.com.stormex.app.library.dto.update.AuthorUpdateDto;
import ar.com.stormex.app.library.mapper.AuthorMapper;
import ar.com.stormex.app.library.model.entity.AuthorEntity;
import ar.com.stormex.app.library.repository.AuthorRepository;
import ar.com.stormex.app.library.response.AbstractResponse;
import ar.com.stormex.app.library.response.ResponseObject;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ES:
 * Servicio que encapsula la lógica para la gestión de los autores.
 *
 * EN:
 * Service that encapsulates the logic for managing authors.
 */
@Data
@Service
public class AuthorService extends AbstractResponse {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    public ResponseObject<List<AuthorDto>> findAll() {
        List<AuthorEntity> authors = getRepository().findAll();
        List<AuthorDto> authorDtoList = getMapper().toDtoList(authors);

        return super.okResponse(authorDtoList,"Authors found",HttpStatus.OK.value());
    }

    public ResponseObject<AuthorDto> create(AuthorCreateDto authorCreateDto) {
        AuthorEntity authorEntity = getMapper().fromCreateDtoToEntity(authorCreateDto);
        authorEntity = getRepository().save(authorEntity);

        AuthorDto authorDto = getMapper().toDto(authorEntity);

        return super.okResponse(authorDto,"Author created successfully",HttpStatus.CREATED.value());
    }

    public ResponseObject<AuthorDto> update(AuthorUpdateDto authorUpdateDto) {
        AuthorEntity authorEntity = getRepository().getReferenceById(authorUpdateDto.getId());

        authorEntity = getMapper().merge(authorEntity, authorUpdateDto);
        authorEntity = getRepository().save(authorEntity);

        ResponseObject<AuthorDto> response = new ResponseObject<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Author updated successfully");
        response.setData(getMapper().toDto(authorEntity));

        return response;
    }

    public ResponseObject<AuthorDto> findById(Long id) {
        Optional<AuthorEntity> optional = getRepository().findById(id);

        if (optional.isEmpty()){
            return super.errorResponse("Author does not found", HttpStatus.NOT_FOUND.value());
        }
        AuthorDto authorDto = getMapper().toDto(optional.get());

        return super.okResponse(authorDto,"Author found",HttpStatus.OK.value());
    }

    public ResponseObject<String> delete(Long id){
        Optional<AuthorEntity> optional = getRepository().findById(id);

        if (optional.isEmpty()){
            return super.errorResponse("Author does not found", HttpStatus.NOT_FOUND.value());
        }
        getRepository().deleteById(id);

        return super.okResponse(null,"Author deleted",HttpStatus.NO_CONTENT.value());
    }
}
