package com.github.matheusmv.beerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BeerNotFoundException.class)
    public ResponseEntity<StandardError> beerNotFoundException(BeerNotFoundException exception,
                                                               HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(BeerAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> beerAlreadyRegisteredException(BeerAlreadyRegisteredException exception,
                                                                        HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(BeerStockExceededException.class)
    public ResponseEntity<StandardError> BeerStockExceededException(BeerStockExceededException exception,
                                                                    HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);

        return ResponseEntity.status(status).body(error);
    }

    private StandardError getStandardError(RuntimeException exception,
                                           HttpServletRequest request,
                                           HttpStatus status) {
        return StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
