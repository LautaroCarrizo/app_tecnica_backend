package com.app.backend.api.exception.exceptions;

import com.app.backend.api.exception.modelException.ApiException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String code, String message) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
}