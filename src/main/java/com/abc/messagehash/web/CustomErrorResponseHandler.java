package com.abc.messagehash.web;

import com.abc.messagehash.buisness.domain.MessageHashNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomErrorResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MessageHashNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleNotFoundException(MessageHashNotFoundException ex) {
        ErrorMessage errMsg = new ErrorMessage(ex.getMessage(), ex.getDigest());

        return new ResponseEntity<>(errMsg, HttpStatus.NOT_FOUND);
    }
}
