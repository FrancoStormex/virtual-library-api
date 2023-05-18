package ar.com.stormex.app.library.repository;

import ar.com.stormex.app.library.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository that defines how to interact with authors in a database. / Repositorio que define como interactuar con los autores en una base de datos.
 */
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByName(String name);

    Optional<AuthorEntity> findByIdNotAndName(Long id, String name);
}
