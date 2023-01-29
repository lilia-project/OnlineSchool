package org.lilia.exception;

public class NoSuchMaterialIdException extends Throwable {
    public NoSuchMaterialIdException(int additionMaterialId) {
        super("no such materialId exist " + additionMaterialId);
    }
}
