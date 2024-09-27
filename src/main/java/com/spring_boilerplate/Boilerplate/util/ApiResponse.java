package com.spring_boilerplate.Boilerplate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int statusCode;
    private boolean success;
    private String message;
    private T data;
    private Map<String, String> errors;

    public ApiResponse(int statusCode, boolean success, String message, T data, Map<String, String> errors) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

}

