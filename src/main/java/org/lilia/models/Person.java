package org.lilia.models;

import static org.lilia.models.Role.STUDENT;
import static org.lilia.models.Role.TEACHER;

public class Person extends Model {
    private static int counter = 0;

    private int courseId;
    private final int id;
    private final String lastName;
    private String phone;
    private String email;

    private final Role role;

    private Person(int courseId, String firstName, Role role, String lastName, String phone, String email) {
        super(firstName, ++counter);
        this.courseId = courseId;
        this.id = counter++;
        this.role = role;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    private Person(String firstName, Role role, String lastName, String phone, String email) {
        super(firstName, ++counter);
        this.id = counter++;
        this.role = role;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    private Person(int courseId, String firstName, Role role, String lastName) {
        super(firstName, ++counter);
        this.id = counter++;
        this.role = role;
        this.lastName = lastName;
    }

    public static Person createTeacher(int courseId, String firstName, String lastName, String phone, String email) {
        Person teacher = new Person(courseId, firstName, TEACHER, lastName, phone, email);
        return teacher;
    }

    public static Person createTeacher(String firstName, String lastName, String phone, String email) {
        Person teacher = new Person(firstName, TEACHER, lastName, phone, email);
        return teacher;
    }

    public static Person createTeacher(int courseId, String firstName, String lastName) {
        Person teacher = new Person(courseId, firstName, TEACHER, lastName);
        return teacher;
    }

    public static Person createStudent(int courseId, String firstName, String lastName, String phone, String email) {
        Person student = new Person(courseId, firstName, STUDENT, lastName, phone, email);
        return student;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return " courseId = " + courseId +
                " teacherId = " + id +
                " teacherFirstName = " + getName() +
                " teacherLastName = " + lastName +
                " teacherPhone = " + phone +
                " teacherEmail = " + email;
    }
}
