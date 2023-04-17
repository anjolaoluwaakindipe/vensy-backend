package com.dalevents.vensy.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dalevents.vensy.common.AppError;
import com.dalevents.vensy.common.AppErrorMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AppControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppError.class)
    ResponseEntity<AppErrorMessage> throwAppErrorError(AppError e, HttpServletRequest request) {
        var appError = new AppErrorMessage(e.getStatusCode(), e.getMessage(), LocalDateTime.now(),
                request.getRequestURI());
        return new ResponseEntity<AppErrorMessage>(appError, HttpStatusCode.valueOf(appError.statusCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<AppErrorMessage> throwInternalServerError(RuntimeException e, HttpServletRequest request) {
        log.error("Error", e);
        var appError = new AppErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error",
                LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<AppErrorMessage>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Map<String, String>> allErrors = new ArrayList<>();
        var allValidationErr = ex.getAllErrors();
        
        for (ObjectError objectError : allValidationErr) {
            allErrors.add(Map.of(((FieldError) objectError).getField(), objectError.getDefaultMessage()));
        }

        var appError = new AppErrorMessage(HttpStatus.BAD_REQUEST.value(), allErrors, LocalDateTime.now(),
                request.getDescription(false).replaceAll("uri=", ""));
        return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
    }

}
