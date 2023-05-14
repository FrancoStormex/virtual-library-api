package ar.com.stormex.app.library.repository;

import ar.com.stormex.app.library.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ES:
 * Repositorio que define como interactuar con los autores en una base de datos.
 *
 * EN:
 * Repository that defines how to interact with authors in a database.
 */
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
