package com.lendico.exception.error;

public enum PlanError implements ErrorEnum<PlanError> {

    NULL_PAYLOAD(1, "Please provide valid payload"),
    NULL_DURATION(2, "Please provide duration"),
    INVALID_DURATION_VALUE(3, "Please provide valid duration"),
    NULL_LOAN_AMOUNT(4, "Please provide load amount"),
    INVALID_LOAN_AMOUNT(5, "Please provide valid loan amount"),
    NULL_NOMINAL_RATE(6, "Please provide nominal rate"),
    INVALID_NOMINAL_RATE(7, "Please provide valid nominal rate"),
    NULL_START_DATE(8, "Please provide start date"),
    INVALID_INVALID_START(9, "Start date could not be in past"),;

    private int errorCode;
    private String errorMessage;

    PlanError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return String.format("PE-%04d", this.errorCode);
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
