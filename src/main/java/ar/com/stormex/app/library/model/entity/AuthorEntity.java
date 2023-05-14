package ar.com.stormex.app.library.model.entity;

import ar.com.stormex.app.library.common.BaseTimeStamp;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * ES:
 * Entidad con el modelo de los autores.
 *
 * EN:
 * Entity with the authors' model.
 */
@Data
@Entity
@Table(name = "author")
public class AuthorEntity extends BaseTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "name")
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "profession")
    private String profession;
}