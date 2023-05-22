package ar.com.stormex.app.library.common;

import jakarta.validation.constraints.NotNull;

/**
 * An interface that provides a contract for objects that have an identifier and defines the methods necessary to get and set that identifier. / Una interfaz que proporciona un contrato para objetos que tienen un identificador y define los métodos necesarios para obtener y establecer dicho identificador.
 * @param <T> Generic type parameter. / Parámetro de tipo genérico.
 */
public interface BaseObject<T> {

    /**
     * This method represents getting the identifier of an object. / Este método representa la obtención del identificador de un objeto.
     *
     * @return Returns a value of generic type. / Devuelve un valor de tipo genérico.
     */
    public T getId();

    /**
     * This method is used to set the identifier of an object and requires that the parameter not be null. Este método se utiliza para establecer el identificador de un objeto y requiere que el parámetro no sea nulo.
     *
     * @param id A value of generic type. / Un valor de tipo genérico.
     */
    public void setId(@NotNull T id);
}
