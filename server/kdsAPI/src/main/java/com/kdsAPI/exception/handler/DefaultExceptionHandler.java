package com.kdsAPI.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kdsAPI.exception.NotFoundException;
import com.kdsAPI.responses.ControllerResponse;
import com.kdsAPI.responses.Response;

import lombok.RequiredArgsConstructor;


@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {

    private final ControllerResponse<Object> response;

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response<Object>> httpRequestMethodNotSupportedExceptionHandling(HttpRequestMethodNotSupportedException exception) {
        return response.errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), null);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Response<Object>> NaoEncontradoExceptionHandling(NotFoundException exception) {
        return response.errorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), null);
    }
}
