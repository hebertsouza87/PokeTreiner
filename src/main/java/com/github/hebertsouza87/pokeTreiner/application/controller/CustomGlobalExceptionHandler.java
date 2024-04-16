package com.github.hebertsouza87.pokeTreiner.application.controller;

import com.github.hebertsouza87.pokeTreiner.application.model.ExceptionJson;
import com.github.hebertsouza87.pokeTreiner.domain.exception.DuplicatedException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.InvalidObjectException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionJson> exception(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();

        if (ex instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof InvalidObjectException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof DuplicatedException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ConstraintViolationException) {
            status = HttpStatus.BAD_REQUEST;
            message = ((ConstraintViolationException) ex).getConstraintViolations().iterator().next().getMessage();
        } else {
            logger.error("An unexpected error occurred", ex);
        }

        return new ResponseEntity<>(new ExceptionJson(message), status);
    }
}