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
 * ES:
 * API orientada a la gestión de autores.
 *
 * EN:
 * API oriented to manage the authors.
 */
@RequestMapping("/authors")
@RestController
@Slf4j
public class AuthorController {

    @Autowired
    AuthorService service;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<AuthorDto> create(@RequestBody AuthorCreateDto authorCreateDto) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "createDto: " + authorCreateDto);
        return this.service.create(authorCreateDto);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<AuthorDto> update(@RequestBody AuthorUpdateDto authorUpdateDto) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "createDto: " + authorUpdateDto);
        return this.service.update(authorUpdateDto);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<String> delete(@PathVariable("id") Long id) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "id: " + id);
        return this.service.delete(id);
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<AuthorDto> findById (@PathVariable("id") Long id) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "id: " + id);
        return this.service.findById(id);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<List<AuthorDto>> findAll() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        return this.service.findAll();
    }

}
