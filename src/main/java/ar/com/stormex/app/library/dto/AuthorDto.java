package ar.com.stormex.app.library.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO to transmit the general information of the authors. DTO para transmitir la información general de los autores.
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
