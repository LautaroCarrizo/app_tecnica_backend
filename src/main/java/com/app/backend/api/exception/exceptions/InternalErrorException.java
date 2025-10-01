package com.app.backend.api.exception.exceptions;

import com.app.backend.api.exception.modelException.ApiException;
import org.springframework.http.HttpStatus;

public class InternalErrorException extends ApiException {
    public InternalErrorException(String code, String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, message);
    }
}
