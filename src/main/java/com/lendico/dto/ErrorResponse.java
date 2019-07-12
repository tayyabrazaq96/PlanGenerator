package com.lendico.dto;

import com.lendico.exception.error.AppError;
import com.lendico.exception.error.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    public static ErrorResponse fail() {
        ErrorEnum error = AppError.SOMETHING_WENT_WRONG;
        return new ErrorResponse(error.getErrorCode(), error.getErrorMessage());
    }

    public static ErrorResponse fail(String errorCode, String errorMessage) {
        return new ErrorResponse(errorCode, errorMessage);
    }
}
