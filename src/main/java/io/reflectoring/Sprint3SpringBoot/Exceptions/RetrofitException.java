package io.reflectoring.Sprint3SpringBoot.Exceptions;

/**
 * Custom exception class for handling Retrofit-related errors.
 */
public class RetrofitException extends RuntimeException {

    public RetrofitException(String message) {
        super(message);
    }
}
