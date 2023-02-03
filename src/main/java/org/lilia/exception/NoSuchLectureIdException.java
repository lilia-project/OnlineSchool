package org.lilia.exception;

public class NoSuchLectureIdException extends Exception {

    public NoSuchLectureIdException(int lectureId) {
        super("no such lectureId exist " + lectureId);
    }
}
