package org.lilia.service;

import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.repository.PersonRepository;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(String lastName, Role role) {
        Person teacher = Person.createPerson(lastName, role);
        personRepository.add(teacher);
        System.out.println("\nthe teacher has been created:\n" + teacher);
        return teacher;
    }

   /* public int[] getAllTeacherIds() {
      //  Person[] teacher =PersonRepository.g
        int[] allTeacherIds = new int[teacher.length];
        for (int i = 0; i < teacher.length; i++) {
            allTeacherIds[i] = teacher[i].getId();
        }
        return allTeacherIds;
    }*/
}
