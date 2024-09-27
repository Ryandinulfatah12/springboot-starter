package com.spring_boilerplate.Boilerplate.exception;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
public class CustomException extends RuntimeException {
    @Getter
    private final int statusCode;
    private final String message;
    @Getter
    private final Map<String, String> errors;

    public CustomException(int statusCode, String message, Map<String, String> errors) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
