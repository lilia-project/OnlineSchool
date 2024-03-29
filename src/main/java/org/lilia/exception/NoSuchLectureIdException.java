package org.lilia.exception;

public class NoSuchLectureIdException extends RuntimeException {

    public NoSuchLectureIdException(int lectureId) {
        super("no such lectureId exist " + lectureId);
    }
}
