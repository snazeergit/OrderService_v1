package com.booking.exception;

import com.booking.model.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleOrderNotFound(
            OrderNotFoundException ex,
            HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails(
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toString(),
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                "ORDER_NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
