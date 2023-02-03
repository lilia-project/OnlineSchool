package org.lilia.model;

public class Person {
    private static int counter = 0;
    private final Integer id;
    private final Role role;
    private final String lastName;
    private String firstName;
    private String phone;
    private String email;
    private int courseId;
    private int lectureId;

    public Person(String lastName, Role role) {
        this.lastName = lastName;
        this.role = role;
        counter++;
        id = counter;
    }


    public static Person createPerson(String lastName, Role role) {
        return new Person(lastName, role);
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

    public int getLectureId() {
        return lectureId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "\n id = " + id +
                "\n Last name = " + lastName +
                "\n First name = " + firstName +
                "\n courseId = " + courseId +
                "\n lectureId = " + lectureId +
                "\n Phone = " + phone +
                "\n Email = " + email;

    }
}
