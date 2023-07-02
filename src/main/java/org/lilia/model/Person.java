package org.lilia.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private Integer id;
    private Role role;

    private String lastName;

    private String firstName;

    private String phone;
    private String email;
    private int courseId;

    public Person(final Integer id, final Role role, String lastName) {
        this.id = id;
        this.role = role;
        this.lastName = lastName;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "\n Person{" +
                "\n id = " + id +
                "\n role = " + role +
                "\n Last name = " + lastName +
                "\n First name = " + firstName +
                "\n courseId = " + courseId +
                "\n Phone = " + phone +
                "\n Email = " + email +
                "\n }";
    }

}
