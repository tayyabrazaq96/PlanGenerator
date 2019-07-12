package com.lendico.exception.custom;

import com.lendico.exception.error.ErrorEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends AppException {

    static final long serialVersionUID = -123456L;

    public ValidationException(ErrorEnum<? extends Enum<?>> errorEnum) {
        super(errorEnum);
    }
}
