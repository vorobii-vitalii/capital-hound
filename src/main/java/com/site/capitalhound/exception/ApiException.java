package com.site.capitalhound.exception;

public class ApiException extends RuntimeException {

    public ApiException() {}

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
