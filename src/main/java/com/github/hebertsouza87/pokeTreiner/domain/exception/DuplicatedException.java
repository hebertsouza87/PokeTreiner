package com.github.hebertsouza87.pokeTreiner.domain.exception;

public class DuplicatedException extends CustomException {
    public DuplicatedException(String message) {
        super(message);
    }
}