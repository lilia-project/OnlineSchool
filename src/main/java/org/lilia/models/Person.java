package org.lilia.models;

import static org.lilia.models.Role.STUDENT;
import static org.lilia.models.Role.TEACHER;

public class Person {
    private static int counter = 0;

    private final Integer id;

    private String firstName;

    private final String lastName;
    private String phone;
    private String email;
    private final Role role;
    private int courseId;

    public Person(String lastName, Role role) {
        this.lastName = lastName;
        this.role = role;
        counter++;
        id = counter;
    }


    public static Person createTeacher(String lastName) {
        Person teacher = new Person(lastName, TEACHER);
        return teacher;
    }

    public static Person createStudent(String lastName) {
        Person student = new Person(lastName, STUDENT);
        return student;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "\n courseId = " + courseId +
                "\n Id = " + id +
                "\n First name = " + firstName +
                "\n Last name = " + lastName +
                "\n Phone = " + phone +
                "\n Email = " + email;

    }
}
