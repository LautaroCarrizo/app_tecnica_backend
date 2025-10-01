package com.app.backend.api.exception.exceptions;

import com.app.backend.api.exception.modelException.ApiException;
import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {
    public ConflictException(String code, String message) {
        super(HttpStatus.CONFLICT, code, message);
    }
}
