package com.mzherdev.restchooser.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mzherdev on 22.09.16.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Vote Update Impossible after 11 a.m.") //406
public class VoteUpdateImpossibleException extends Exception {
    public VoteUpdateImpossibleException(String message) {
        super(message);
    }
}
