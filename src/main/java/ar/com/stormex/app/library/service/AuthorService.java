package ar.com.stormex.app.library.service;

import ar.com.stormex.app.library.common.DefaultService;
import ar.com.stormex.app.library.dto.AuthorDto;
import ar.com.stormex.app.library.dto.create.AuthorCreateDto;
import ar.com.stormex.app.library.dto.update.AuthorUpdateDto;
import ar.com.stormex.app.library.mapper.AuthorMapper;
import ar.com.stormex.app.library.model.entity.AuthorEntity;
import ar.com.stormex.app.library.repository.AuthorRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    protected String getName() {
        return "Author";
    }

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
}
