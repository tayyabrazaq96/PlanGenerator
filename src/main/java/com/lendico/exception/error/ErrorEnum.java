package com.lendico.exception.error;

public interface ErrorEnum<E extends Enum<E>> {

    String getErrorCode();
    String getErrorMessage();
}
