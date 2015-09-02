package com.sia.tutorial.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Oleg.Bezkorovaynyi on 31 Aug 2015.
 *
 * Wide exception handler.
 */
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public String duplicateUserHandler() {
        return "error/duplicate";
    }

}
