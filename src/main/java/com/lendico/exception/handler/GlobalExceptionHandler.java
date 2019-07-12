package com.lendico.exception.handler;

import com.lendico.dto.ErrorResponse;
import com.lendico.exception.custom.AppException;
import com.lendico.exception.error.ErrorEnum;
import com.lendico.exception.error.AppError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger("errorLogger");

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse exceptionHandler(HttpServletRequest req, RuntimeException e) {
        LOGGER.error(String.format("Failed Request | URL: %s | ErrorMessage: %s", req.getRequestURL(), e.getMessage()), e.getStackTrace());
        return ErrorResponse.fail();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException e) {
        ErrorEnum error = AppError.INVALID_ENDPOINT;
        LOGGER.error(String.format("Failed Request | URL: %s | ErrorMessage: %s", req.getRequestURL(), e.getMessage()), e.getStackTrace());
        return ErrorResponse.fail(error.getErrorCode(), error.getErrorMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AppException.class)
    public ErrorResponse exceptionHandler(HttpServletRequest req, AppException e) {
        LOGGER.error(String.format("Failed Request | URL: %s | ErrorCode: %s | ErrorMessage: %s", req.getRequestURL(), e.getErrorCode(), e.getErrorMessage()), e.getStackTrace());
        return ErrorResponse.fail(e.getErrorCode(), e.getErrorMessage());
    }
}
