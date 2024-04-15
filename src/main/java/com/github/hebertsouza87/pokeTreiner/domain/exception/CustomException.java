package com.github.hebertsouza87.pokeTreiner.domain.exception;

public abstract class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
