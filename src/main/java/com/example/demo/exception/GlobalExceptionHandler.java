package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理 @Valid 参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.putIfAbsent(
                        error.getField(),
                        error.getDefaultMessage()
                ));

        ErrorResponse response = new ErrorResponse(
                400,
                "Validation failed",
                errors
        );

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    // 处理 Service 中主动抛出的 HTTP 异常
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleStatus(
            ResponseStatusException ex
    ) {
        int status = ex.getStatusCode().value();

        ErrorResponse response = new ErrorResponse(
                status,
                ex.getReason(),
                Map.of()
        );

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(response);
    }

    // 兜底：处理未预期的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(
          Exception ex
    ) {
        ErrorResponse response = new ErrorResponse(
                500,
                "Internal server error",
                Map.of()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}
