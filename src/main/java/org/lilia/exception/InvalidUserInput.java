package org.lilia.exception;

public class InvalidUserInput extends RuntimeException {
    public InvalidUserInput(String data, String pattern) {
        super("expected String that matches pattern: " + pattern + " , got: " + data);
    }
}
