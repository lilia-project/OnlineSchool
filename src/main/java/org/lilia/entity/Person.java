package org.lilia.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
@Data
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "text")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "course_id")
    private int courseId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    public Person() {
    }

    public Person(final Integer id, final Role role, String lastName) {
        this.id = id;
        this.role = role;
        this.lastName = lastName;
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
