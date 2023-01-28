package org.lilia.models;

import static org.lilia.models.Role.STUDENT;
import static org.lilia.models.Role.TEACHER;

public class Person{
    private static int counter = 0;

    private final Integer id;

    private final String firstName;

    private final String lastName;
    private final String phone;
    private final String email;
    private final Role role;
    private int courseId;
    public Person(int courseId, String firstName, String lastName, String phone, String email, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.courseId = courseId;
        counter++;
        id = counter;
    }


    public static Person createTeacher(int courseId, String firstName, String lastName, String phone, String email) {
        Person teacher = new Person(courseId, firstName, lastName, phone, email, TEACHER);
        return teacher;
    }

    public static Person createStudent(int courseId, String firstName, String lastName, String phone, String email) {
        Person student = new Person(courseId, firstName, lastName, phone, email, STUDENT);
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
        return " courseId = " + courseId +
                " teacherId = " + id +
                " teacherFirstName = " + firstName +
                " teacherLastName = " + lastName +
                " teacherPhone = " + phone +
                " teacherEmail = " + email;
    }
}
