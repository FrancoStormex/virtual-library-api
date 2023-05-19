package ar.com.stormex.app.library.response;

/**
 * Class dedicated to the construction of answers. / Clase dedicada a la construcción de las respuestas.
 */
public class AbstractResponse {

    /**
     * Create a response object when the request to the server has been successful. / Crea un objeto de respuesta cuando la solicitud al servidor ha tenido exito.
     * @param dto Generic DTO.
     * @param message Response message.
     * @param statusCode Response code.
     * @param <T> Generic type parameter.
     * @return It returns a response object of the generic type, whose interior contains the complete response information.
     */
    public <T> ResponseObject<T> okResponse(T dto, String message, int statusCode) {
        ResponseObject<T> response = new ResponseObject<>();
        response.setData(dto);
        response.setMessage(message);
        response.setStatus(statusCode);

        return response;
    }

    /**
     * Returns a response object of the generic type, whose interior contains the complete information of the response. / Crea un objeto de respuesta cuando hay un conflicto al procesar la solicitud.
     * @param message Response message.
     * @param statusCode Response code.
     * @param <T> Generic type parameter.
     * @return It returns a response object of the generic type, whose interior contains the complete response information.
     */
    public <T> ResponseObject<T> errorResponse(String message, int statusCode) {
        ResponseObject<T> response = new ResponseObject<>();
        response.setData(null);
        response.setMessage(message);
        response.setStatus(statusCode);

        return response;
    }
}
