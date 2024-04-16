package com.github.hebertsouza87.pokeTreiner.application.controller;

import com.github.hebertsouza87.pokeTreiner.application.model.ExceptionJson;
import com.github.hebertsouza87.pokeTreiner.domain.exception.DuplicatedException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.InvalidObjectException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomGlobalExceptionHandlerTest {

    private CustomGlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new CustomGlobalExceptionHandler();
    }

    @Test
    void handleNotFoundException() {
        NotFoundException ex = new NotFoundException("Not found");
        ResponseEntity<ExceptionJson> response = handler.exception(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found", response.getBody().message());
    }

    @Test
    void handleInvalidObjectException() {
        InvalidObjectException ex = new InvalidObjectException("Invalid object");
        ResponseEntity<ExceptionJson> response = handler.exception(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid object", response.getBody().message());
    }

    @Test
    void handleDuplicatedException() {
        DuplicatedException ex = new DuplicatedException("Duplicated object");
        ResponseEntity<ExceptionJson> response = handler.exception(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Duplicated object", response.getBody().message());
    }

    @Test
    void handleGeneralException() {
        Exception ex = new Exception("General exception");
        ResponseEntity<ExceptionJson> response = handler.exception(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("General exception", response.getBody().message());
    }
}