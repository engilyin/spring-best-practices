package com.engilyin.bestpractices.rest.controllers;

import com.engilyin.bestpractices.rest.controllers.data.ApiError;
import com.engilyin.bestpractices.rest.controllers.data.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ApiError> missingRequestHeaderException(MissingRequestHeaderException e) {
        var apiError = new ApiError(
                "MRH",
                "missing-request-header",
                new ErrorDetail(e.getHeaderName(), "required", "The header is required"));
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorDetail[] details =
                e.getFieldErrors().stream().map(this::mapFieldErrorsToDetails).toArray(ErrorDetail[]::new);

        var apiError = new ApiError("MANV", "method-argument-not-valid", details);

        return ResponseEntity.badRequest().body(apiError);
    }

    private ErrorDetail mapFieldErrorsToDetails(FieldError fieldError) {
        return new ErrorDetail(fieldError.getField(), "invalid-field", "Invalid field");
    }
}
