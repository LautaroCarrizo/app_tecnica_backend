package com.app.backend.api.exception.exceptions;

import com.app.backend.api.exception.modelException.ApiException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {
    public BadRequestException(String code, String message){
        super(HttpStatus.BAD_REQUEST, code, message);
    }
}
