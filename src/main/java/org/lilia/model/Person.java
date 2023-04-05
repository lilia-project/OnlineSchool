package org.lilia.model;

import java.util.Comparator;

public class Person {
    private static int counter = 0;
    private final Integer id;
    private final Role role;

    private String lastName;

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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static class sortByLastName implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    }
}
