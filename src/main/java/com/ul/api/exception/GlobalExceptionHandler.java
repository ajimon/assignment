package com.ul.api.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * To handle all exception in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param ex
     * @param request
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(
            final ResourceNotFoundException ex, final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex
     * @param request
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(final Exception ex,
            final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
