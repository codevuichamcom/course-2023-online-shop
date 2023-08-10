package com.funnycode.onlineshop.exception;

import com.funnycode.onlineshop.model.CustomError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OnlineShopException.class)
    public ResponseEntity<CustomError> handleOnlineShopException(OnlineShopException ex) {
        return new ResponseEntity<CustomError>(ex.getError(), ex.getStatus());
    }
}
