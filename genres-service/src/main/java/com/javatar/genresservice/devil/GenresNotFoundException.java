package com.javatar.genresservice.devil;

public class GenresNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1219518716563440469L;

    public GenresNotFoundException(String message) {
        super(message);
    }

    public GenresNotFoundException(String message, Throwable th) {
        super(message, th);
    }
}