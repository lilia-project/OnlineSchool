package org.lilia.exception;

public class InvalidUserInputException extends RuntimeException {
    public InvalidUserInputException(String data, String pattern) {
        super("expected String that matches pattern: " + pattern + " , got: " + data);
    }
}
