package com.github.hebertsouza87.pokeTreiner.application.controller;

import com.github.hebertsouza87.pokeTreiner.application.model.ExceptionJson;
import com.github.hebertsouza87.pokeTreiner.domain.exception.DuplicatedException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.InvalidObjectException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionJson> exception(Exception ex) {
        HttpStatus status = ex instanceof NotFoundException ? HttpStatus.NOT_FOUND
                : ex instanceof InvalidObjectException ? HttpStatus.BAD_REQUEST
                : ex instanceof DuplicatedException ? HttpStatus.BAD_REQUEST
                : HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(new ExceptionJson(ex.getMessage()), status);
    }
}