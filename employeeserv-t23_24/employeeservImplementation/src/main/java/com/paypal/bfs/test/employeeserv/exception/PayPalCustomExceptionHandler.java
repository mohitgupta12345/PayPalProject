package com.paypal.bfs.test.employeeserv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PayPalCustomExceptionHandler {

    @ExceptionHandler(PayPalCustomException.class)
    public ResponseEntity<String> handlePayPalRuntimeExceptionInternal(HttpServletRequest request, PayPalCustomException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessg());
    }
}
