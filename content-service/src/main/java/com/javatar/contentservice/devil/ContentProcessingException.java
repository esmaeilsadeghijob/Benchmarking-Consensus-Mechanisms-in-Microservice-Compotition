package com.javatar.contentservice.devil;

public class ContentProcessingException extends RuntimeException {

    private static final long serialVersionUID = 8337796345425999746L;

    public ContentProcessingException(String message) {
        super(message);
    }

    public ContentProcessingException(String message, Throwable th) {
        super(message, th);
    }

}