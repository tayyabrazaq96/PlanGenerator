package com.lendico.exception.error;

public enum AppError implements ErrorEnum<AppError> {

    SOMETHING_WENT_WRONG(1, "Ooops! Something went wrong. Please check with admin"),
    INVALID_ENDPOINT(2, "Sorry! There's no mapping of request");

    private int errorCode;
    private String errorMessage;

    AppError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return String.format("AE-%04d", this.errorCode);
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
