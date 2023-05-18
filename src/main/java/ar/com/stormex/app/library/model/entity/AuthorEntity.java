package ar.com.stormex.app.library.model.entity;

import ar.com.stormex.app.library.common.BaseObject;
import ar.com.stormex.app.library.common.BaseTimeStamp;
import ar.com.stormex.app.library.model.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Entity with the authors' model. / Entidad con el modelo de los autores.
 */
@Data
@Entity
@Table(name = "author")
public class AuthorEntity extends BaseTimeStamp implements BaseObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "name")
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "profession")
    private String profession;
}