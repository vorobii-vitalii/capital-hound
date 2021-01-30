package com.site.capitalhound.exception.handler;

import com.site.capitalhound.exception.ApiException;
import com.site.capitalhound.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public String handleApiException(ApiException e) {
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException e) {
        return "error";
    }

}
