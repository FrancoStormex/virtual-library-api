package ar.com.stormex.app.library.common;

import jakarta.validation.constraints.NotNull;

public interface BaseObject<T> {

    public T getId();

    public void setId(@NotNull T id);
}
