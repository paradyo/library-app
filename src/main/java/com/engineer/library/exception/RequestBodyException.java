package com.engineer.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestBodyException extends Exception {
    public RequestBodyException() {
    }

    public RequestBodyException(String message) {
        super(message);
    }
}
