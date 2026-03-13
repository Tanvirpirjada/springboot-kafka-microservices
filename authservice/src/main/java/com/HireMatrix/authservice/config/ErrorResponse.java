package com.HireMatrix.authservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private Object message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
