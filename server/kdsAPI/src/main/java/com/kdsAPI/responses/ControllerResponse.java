package com.kdsAPI.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ControllerResponse<T> {

    public ResponseEntity<Response<T>> ok(T type) {
        Response<T> response = new Response<>(HttpStatus.OK.value(), null, type);
        return new ResponseEntity<Response<T>>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response<T>> customResponse(HttpStatus status, T type) {
        Response<T> response = new Response<>(status.value(), null, type);
        return new ResponseEntity<Response<T>>(response, status);
    }

    public ResponseEntity<Response<T>> errorResponse(HttpStatus status, String error, T type) {
        Response<T> response = new Response<>(status.value(), null, type);
        return new ResponseEntity<Response<T>>(response, status);
    }
}
