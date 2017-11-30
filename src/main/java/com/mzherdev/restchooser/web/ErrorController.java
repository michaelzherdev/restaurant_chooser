package com.mzherdev.restchooser.web;

import com.mzherdev.restchooser.util.exception.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/errors")
public class ErrorController {

    Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("resourceNotFound")
    @ResponseBody
        public ErrorInfo resourceNotFound(HttpServletRequest request) throws Exception {
        String url = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        LOG.error("Exception at request " + url);
        return new ErrorInfo(url, "404", "Resource not found");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping("unauthorized")
    @ResponseBody
    public ErrorInfo unauthorised(HttpServletRequest request) throws Exception {
        String url = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        LOG.error("Exception at request " + url);
        return new ErrorInfo(url, "401", "Authentication Failed");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @RequestMapping("badCredentials")
    @ResponseBody
    public ErrorInfo badCredentials(HttpServletRequest request) throws Exception {
        String url = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        LOG.error("Exception at request " + url);
        return new ErrorInfo(url, "403", "Access is forbidden");
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @RequestMapping("notAcceptable")
    @ResponseBody
    public ErrorInfo notAcceptable(HttpServletRequest request, Exception e) throws Exception {
        String url = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        LOG.error("Exception at request " + url);
        return new ErrorInfo(url, e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping("serverError")
    @ResponseBody
    public ErrorInfo serverError(HttpServletRequest request, Exception e) throws Exception {
        String url = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        LOG.error("Exception at request " + url);
        return new ErrorInfo(url, e);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest request, Exception e) {
        String url = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
        LOG.error("Exception at request " + url);
        return new ErrorInfo(url, e);
    }
}
