package org.lilia.repository;

import org.lilia.model.Lecture;

public interface SimpleIterator {

    boolean hasNext();

    Lecture next();

    void remove();
}
