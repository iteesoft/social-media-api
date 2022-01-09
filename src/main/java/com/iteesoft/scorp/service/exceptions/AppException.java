package com.iteesoft.scorp.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
