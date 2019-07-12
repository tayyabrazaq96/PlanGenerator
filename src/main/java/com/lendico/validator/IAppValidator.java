package com.lendico.validator;

@FunctionalInterface
public interface IAppValidator<R> {

    void validate(R request);
}
