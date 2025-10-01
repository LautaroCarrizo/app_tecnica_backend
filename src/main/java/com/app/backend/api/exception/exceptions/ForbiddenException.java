package com.app.backend.api.exception.exceptions;

import com.app.backend.api.exception.modelException.ApiException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String code, String message) {
        super(HttpStatus.FORBIDDEN, code, message);
    }
}
