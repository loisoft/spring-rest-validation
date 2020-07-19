package com.example.demo.validation.controller;

import com.example.demo.validation.dto.ErrorDto;
import com.example.demo.validation.exception.UnsafeSourceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class BaseControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.joining(";"));
        return new ResponseEntity(new ErrorDto(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsafeSourceException.class)
    public ResponseEntity<Object> handleUnsafeSourceException(UnsafeSourceException exception) {
        return new ResponseEntity(new ErrorDto("Unsafe Source"), HttpStatus.CONFLICT);

    }
}
