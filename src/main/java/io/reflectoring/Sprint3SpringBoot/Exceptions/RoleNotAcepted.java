package io.reflectoring.Sprint3SpringBoot.Exceptions;

public class RoleNotAcepted extends RuntimeException {
    public RoleNotAcepted(String message) {
        super(message);
    }
}
