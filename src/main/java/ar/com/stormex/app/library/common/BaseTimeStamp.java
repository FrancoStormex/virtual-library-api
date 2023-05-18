package ar.com.stormex.app.library.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Abstract class with the information for the creation and update of date and time in the database.
 * It is used as the base for entities.
 * Allows to automatically keep track of the date and time of creation or last update.
 * /
 * Clase abstracta con la información para la creación y actualización de fecha y hora en base de datos.
 * Se utiliza como base para las entidades.
 * Permite para mantener automáticamente un registro de la fecha y hora de la creación o la última actualización.
 *
 * @author franco.martinez
 */
@Data
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseTimeStamp {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    protected Date creationDate = new Date();

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    protected Date lastUpdate = new Date();
}
