package com.richardmurphy.palindrome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
        reason = "Invalid request: Please supply a phrase containing only letters and no spaces")
public class BadRequestException extends RuntimeException {}
