package com.dalevents.vensy.common;

import java.time.LocalDateTime;

public class AppError extends Error {

    int statusCode;
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LocalDateTime timestamp = LocalDateTime.now();
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public AppError(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

}
