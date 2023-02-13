package org.lilia.exception;

public class NoSuchHomeworkException extends RuntimeException {
    public NoSuchHomeworkException(int homeworkId) {
        super("no such homeworkId exist " + homeworkId);
    }
}
