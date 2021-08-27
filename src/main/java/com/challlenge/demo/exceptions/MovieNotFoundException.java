package com.challlenge.demo.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException() {
        super("Movie not found");
    }
}
