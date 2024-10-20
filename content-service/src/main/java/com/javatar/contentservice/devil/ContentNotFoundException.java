package com.javatar.contentservice.devil;

public class ContentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1219518716563440469L;

    public ContentNotFoundException(String message) {
        super(message);
    }

    public ContentNotFoundException(String message, Throwable th) {
        super(message, th);
    }
}