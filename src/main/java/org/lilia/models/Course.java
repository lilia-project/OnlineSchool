package org.lilia.models;

public class Course extends Model {
    private static int counter = 0;

    public Course(String name) {
        super(name, ++counter);
    }
}
