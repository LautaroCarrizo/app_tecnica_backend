package com.app.backend.api.exception.modelException;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiErrorDTO {
    private OffsetDateTime timestamp = OffsetDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;
    private String code;
    private String traceId;

    public ApiErrorDTO(int status, String error, String message, String path, String code) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.code = code;
    }
}
