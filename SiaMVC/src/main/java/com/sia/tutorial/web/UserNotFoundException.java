package com.sia.tutorial.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Oleg.Bezkorovaynyi on 31 Aug 2015.
 *
 * handling custom exceptions
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {
}
