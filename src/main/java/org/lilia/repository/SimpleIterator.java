package org.lilia.repository;

import org.lilia.models.Lecture;

public interface SimpleIterator {

    boolean hasNext();

    Lecture next();

    void remove();
}
