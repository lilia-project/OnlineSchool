package org.lilia.models;

import static org.lilia.models.Role.TEACHER;

public class Person extends Model {
    private static int counter = 0;

    private final int courseId;
    private final int id;

    private final Role role;

    private Person(int courseId, String name, Role role) {
        super(name, ++counter);
        this.courseId = courseId;
        this.id = counter++;
        this.role = role;
    }

    public static Person createTeacher(int courseId, String name) { // todo забыла прописат параметры
        Person teacher = new Person(courseId, name, TEACHER);
        return teacher;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return " idCourse = " + courseId +
                " personId = " + id +
                " nameTeacher = " + getName();
    }
}
