package com.github.prebiusta.exception;

public class SimpleFileLoggerException extends RuntimeException {
    public SimpleFileLoggerException(String message, Exception e) {
        super(message, e);
    }
}
