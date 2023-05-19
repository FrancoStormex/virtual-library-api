package ar.com.stormex.app.library.controller;

import ar.com.stormex.app.library.dto.AuthorDto;
import ar.com.stormex.app.library.dto.create.AuthorCreateDto;
import ar.com.stormex.app.library.dto.update.AuthorUpdateDto;
import ar.com.stormex.app.library.response.ResponseObject;
import ar.com.stormex.app.library.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API oriented to manage the authors. / API orientada a la gestión de autores.
 */
@RequestMapping("/authors")
@RestController
@Slf4j
public class AuthorController {

    @Autowired
    AuthorService service;

    /**
     * It allows to create an author. / Permite crear un autor.
     *
     * @param authorCreateDto The author information to create. / La información del autor que se va a crear.
     * @return Complete author information created. / La información completa del autor creado.
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<AuthorDto> create(@RequestBody AuthorCreateDto authorCreateDto) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "createDto: " + authorCreateDto);
        return this.service.create(authorCreateDto);
    }

    /**
     * It allows updating the data of an existing author. / Permite actualizar los datos de un autor existente.
     *
     * @param authorUpdateDto The author information to update. / La información del autor que se va a actualizar.
     * @return Complete author information updated. / La información completa del autor actualizado.
     */
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<AuthorDto> update(@RequestBody AuthorUpdateDto authorUpdateDto) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "createDto: " + authorUpdateDto);
        return this.service.update(authorUpdateDto);
    }

    /**
     * It allows to search for an author by id. / Permite buscar un autor por id.
     *
     * @param id The id of the author to find. / El id del autor a buscar.
     * @return The complete information of the author that is sought. / La información completa del autor que se busca.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<AuthorDto> findById(@PathVariable("id") Long id) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "id: " + id);
        return this.service.findById(id);
    }

    /**
     * It allows deleting an author registered in the database. / Permite borrar un autor registrado en base de datos.
     *
     * @param id The id of the author to delete. / El id del autor a borrar.
     * @return Deleted message. / Mensaje de eliminado.
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<String> delete(@PathVariable("id") Long id) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "id: " + id);
        return this.service.delete(id);
    }

    /**
     * It allows you to search for all authors. / Permite realizar la búsqueda de todos los autores.
     *
     * @return A list of authors. / Una lista de autores.
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<List<AuthorDto>> findAll() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        return this.service.findAll();
    }
}
