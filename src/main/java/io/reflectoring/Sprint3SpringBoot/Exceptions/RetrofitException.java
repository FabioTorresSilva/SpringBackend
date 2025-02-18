package io.reflectoring.Sprint3SpringBoot.Exceptions;

/**
 * Custom exception class for handling Retrofit-related errors.
 */
public class RetrofitException extends RuntimeException {

    private final int statusCode;

    /**
     * Constructs a new RetrofitException.
     *
     * @param message The error message.
     */
    public RetrofitException(String message) {
        super(message + " (HTTP Status: " + statusCode + ")");
        this.statusCode = statusCode;
    }

    /**
     * Gets the HTTP status code of the failed request.
     *
     * @return The HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }
}
