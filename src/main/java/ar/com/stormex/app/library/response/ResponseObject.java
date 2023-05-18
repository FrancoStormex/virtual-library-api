package ar.com.stormex.app.library.response;

import lombok.Data;

/**
 * Generic class that represents a response object that can work with different types of data.¨/ Clase genérica  que representa un objeto de respuesta que puede trabajar con diferentes tipos de datos.
 * @param <T> a generic parameter.
 */
@Data
public class ResponseObject<T> {

    private int status;
    private String message;
    private T data;
}
