package org.lilia.exception;

public class NoSuchPersonException extends RuntimeException {
    public NoSuchPersonException(String lastName) {
        super("no such person lastname exist " + lastName);
    }
}
