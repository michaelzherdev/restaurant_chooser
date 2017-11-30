package com.mzherdev.restchooser.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Vote Update Impossible after 11 a.m.") //406
public class VoteUpdateImpossibleException extends RuntimeException {
    public VoteUpdateImpossibleException(String message) {
        super(message);
    }
}
