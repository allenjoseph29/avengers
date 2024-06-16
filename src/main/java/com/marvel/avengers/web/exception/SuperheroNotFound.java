package com.marvel.avengers.web.exception;

public class SuperheroNotFound extends RuntimeException {
    public SuperheroNotFound(String message) {
        super(message);
    }

    public static SuperheroNotFound forId(Long id) {
        return new SuperheroNotFound("Superhero with id " + id + " not found");
    }
}
