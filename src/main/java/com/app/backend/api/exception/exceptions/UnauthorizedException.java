package com.app.backend.api.exception.exceptions;

import com.app.backend.api.exception.modelException.ApiException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String code, String message) {
        super(HttpStatus.UNAUTHORIZED, code, message);
    }
}
