package com.dalevents.vensy.common;

public class AppError extends Error {

    int statusCode;
    String message;

    public AppError(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

}
