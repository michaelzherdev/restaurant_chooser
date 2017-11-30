package com.mzherdev.restchooser.util.exception;

public class ErrorInfo {
    public final String url;
    public final String cause;
    public final String detail;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }

    public ErrorInfo(CharSequence url, String cause, String detail) {
        this.url = url.toString();
        this.cause = cause;
        this.detail = detail;
    }
}
