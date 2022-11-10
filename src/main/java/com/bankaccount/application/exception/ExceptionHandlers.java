package com.bankaccount.application.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    private static final String INSUFFICIENT_BALANCE_EXCEPTION = "Debit operation aborted : Insufficient Balance";

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Object> handleInsufficientBalance(InsufficientBalanceException ex) {
        log.error(INSUFFICIENT_BALANCE_EXCEPTION);

        return buildResponseEntity(
                new ResponseError(BAD_REQUEST, INSUFFICIENT_BALANCE_EXCEPTION)
        );
    }

    private ResponseEntity<Object> buildResponseEntity(ResponseError responseError) {
        return new ResponseEntity<>(responseError, responseError.getStatus());
    }
}