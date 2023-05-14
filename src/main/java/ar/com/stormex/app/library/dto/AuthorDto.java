package ar.com.stormex.app.library.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * ES:
 * DTO general para transmitir la información de los autores.
 *
 * EN:
 * General DTO to transmit the information of the authors.
 */
@Data
public class AuthorDto {

    private Long id;

    private LocalDate birthDate;

    private String gender;

    private String name;

    private String nationality;

    private String profession;
}
