package com.mzherdev.restchooser.web;

import com.mzherdev.restchooser.util.exception.NotFoundException;
import com.mzherdev.restchooser.util.exception.VoteUpdateImpossibleException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class SpringSecurityEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // 401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {
        // 403
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
    }

    @ExceptionHandler (value = {NotFoundException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         NotFoundException exception) throws IOException {
        // 404
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found : " + exception.getMessage());
    }

    @ExceptionHandler (value = {VoteUpdateImpossibleException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         VoteUpdateImpossibleException exception) throws IOException {
        // 406
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Not Acceptable : " + exception.getMessage());
    }

    @ExceptionHandler (value = {DataIntegrityViolationException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         DataIntegrityViolationException exception) throws IOException {
        // 409
        response.sendError(HttpServletResponse.SC_CONFLICT, "Not Acceptable : " + exception.getMessage());
    }

    @ExceptionHandler (value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         Exception exception) throws IOException {
        // 500
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
    }

}