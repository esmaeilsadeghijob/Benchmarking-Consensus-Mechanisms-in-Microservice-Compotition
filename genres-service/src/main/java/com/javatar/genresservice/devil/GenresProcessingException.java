package com.javatar.genresservice.devil;

public class GenresProcessingException extends RuntimeException {

    private static final long serialVersionUID = 8337796345425999746L;

    public GenresProcessingException(String message) {
        super(message);
    }

    public GenresProcessingException(String message, Throwable th) {
        super(message, th);
    }

}