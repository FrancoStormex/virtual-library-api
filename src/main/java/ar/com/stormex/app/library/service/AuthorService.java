package ar.com.stormex.app.library.service;

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

import java.util.List;

/**
 * ES:
 * Servicio que encapsula la lógica para la gestión de los autores.
 *
 * EN:
 * Service that encapsulates the logic for managing authors.
 */
@Data
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    public ResponseObject<List<AuthorDto>> findAll() {
        List<AuthorEntity> authors = getRepository().findAll();
        List<AuthorDto> authorDtoList = getMapper().toDtoList(authors);

        ResponseObject<List<AuthorDto>> response = new ResponseObject<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Authors found:");
        response.setData(authorDtoList);

        return response;
    }

    public ResponseObject<AuthorDto> create(AuthorCreateDto authorCreateDto) {

        AuthorEntity authorEntity = getMapper().fromCreateDtoToEntity(authorCreateDto);
        getRepository().save(authorEntity);

        AuthorDto authorDto = getMapper().toDto(authorEntity);

        ResponseObject<AuthorDto> response = new ResponseObject<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Author created successfully");
        response.setData(authorDto);

        return response;
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
}
