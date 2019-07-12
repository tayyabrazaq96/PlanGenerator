package com.lendico.exception.custom;

import com.lendico.exception.error.AppError;
import com.lendico.exception.error.ErrorEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AppException extends RuntimeException {

    private static final long serialVersionUID = 123456789L;

    private String errorCode;
    private String errorMessage;

    public AppException(Exception exception) {
        super(exception);
        this.errorCode = AppError.SOMETHING_WENT_WRONG.getErrorCode();
        this.errorMessage = AppError.SOMETHING_WENT_WRONG.getErrorMessage();
    }

    public AppException(ErrorEnum<? extends Enum<?>> errorEnum) {
        super();
        this.errorCode = errorEnum.getErrorCode();
        this.errorMessage = errorEnum.getErrorMessage();
    }

    public AppException(ErrorEnum<? extends Enum<?>> errorEnum, Exception exception) {
        super(exception);
        this.errorCode = errorEnum.getErrorCode();
        this.errorMessage = errorEnum.getErrorMessage();
    }
}
