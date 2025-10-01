package com.app.backend.api.exception.globalHandler;


import com.app.backend.api.exception.modelException.ApiErrorDTO;
import com.app.backend.api.exception.modelException.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorDTO> onApiException(ApiException ex, HttpServletRequest req) {
        HttpStatus st = ex.getStatus();
        ApiErrorDTO body = new ApiErrorDTO(st.value(), st.getReasonPhrase(), ex.getMessage(), req.getRequestURI(), ex.getCode());
        return ResponseEntity.status(st).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDTO> onConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        String msg = ex.getConstraintViolations().stream()
                .map(this::formatViolation)
                .collect(Collectors.joining("; "));
        HttpStatus st = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(st)
                .body(new ApiErrorDTO(st.value(), st.getReasonPhrase(), msg, req.getRequestURI(), "validation.error"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorDTO> onTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        HttpStatus st = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(st)
                .body(new ApiErrorDTO(st.value(), st.getReasonPhrase(), "Parámetro inválido: " + ex.getName(), req.getRequestURI(), "request.param_type"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorDTO> onDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        HttpStatus st = HttpStatus.CONFLICT;
        return ResponseEntity.status(st)
                .body(new ApiErrorDTO(st.value(), st.getReasonPhrase(), "Violación de integridad de datos", req.getRequestURI(), "db.integrity_violation"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> onGeneric(Exception ex, HttpServletRequest req) {
        ex.printStackTrace();
        HttpStatus st = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(st)
                .body(new ApiErrorDTO(st.value(), st.getReasonPhrase(), "Error inesperado", req.getRequestURI(), "internal.error"));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        String msg = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getField() + ": " + (fe.getDefaultMessage() == null ? "valor inválido" : fe.getDefaultMessage())).collect(Collectors.joining("; "));
        HttpServletRequest req = ((org.springframework.web.context.request.NativeWebRequest) request).getNativeRequest(HttpServletRequest.class);
        return ResponseEntity.status(status).body(new ApiErrorDTO(status.value(), status.toString(), msg.isBlank() ? "Datos inválidos" : msg, req != null ? req.getRequestURI() : null, "validation.body_invalid"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        HttpServletRequest req = ((org.springframework.web.context.request.NativeWebRequest) request).getNativeRequest(HttpServletRequest.class);
        return ResponseEntity.status(status).body(new ApiErrorDTO(status.value(), status.toString(), "JSON inválido o cuerpo ausente", req != null ? req.getRequestURI() : null, "request.body_invalid"));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        HttpServletRequest req = ((org.springframework.web.context.request.NativeWebRequest) request).getNativeRequest(HttpServletRequest.class);
        return ResponseEntity.status(status).body(new ApiErrorDTO(status.value(), status.toString(), "Falta parámetro: " + ex.getParameterName(), req != null ? req.getRequestURI() : null, "request.param_missing"));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        HttpServletRequest req = ((org.springframework.web.context.request.NativeWebRequest) request).getNativeRequest(HttpServletRequest.class);
        return ResponseEntity.status(status).body(new ApiErrorDTO(status.value(), status.toString(), "Recurso no encontrado", req != null ? req.getRequestURI() : null, "route.not_found"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        HttpServletRequest req = ((org.springframework.web.context.request.NativeWebRequest) request).getNativeRequest(HttpServletRequest.class);
        return ResponseEntity.status(status).body(new ApiErrorDTO(status.value(), status.toString(), "Método no permitido", req != null ? req.getRequestURI() : null, "route.method_not_allowed"));
    }

    private String formatViolation(ConstraintViolation<?> cv) {
        return cv.getPropertyPath() + ": " + cv.getMessage();
    }
}
