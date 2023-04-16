package org.lilia.service;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.PersonDto;
import org.lilia.exception.NoSuchPersonException;
import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.repository.PersonRepository;

import java.util.Optional;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(String lastName, Role role) {
        if (lastName == null | role == null) {
            throw new IllegalArgumentException("last name or role is null");
        }
        Person person = Person.createPerson(lastName, role);
        ConsoleUtils.print(Constants.ELEMENT_CREATED + person);
        personRepository.add(person);
        return person;
    }

    public PersonDto createPersonDto(String lastName, String firstName, String phone, String email, int courseId) {

        return new PersonDto(lastName, firstName, phone, email, courseId);
    }

    public Person updatePerson(Person person, PersonDto personDto) {
        if (personDto.getLastName() != null) {
            person.setLastName(personDto.getLastName());
        }
        if (personDto.getFirstName() != null) {
            person.setFirstName(personDto.getFirstName());
        }
        if (personDto.getPhone() != null) {
            person.setPhone(personDto.getPhone());
        }
        if (personDto.getEmail() != null) {
            person.setEmail(personDto.getEmail());
        }
        if (personDto.getCourseId() != 0) {
            person.setCourseId(personDto.getCourseId());
        }
        return person;
    }

    public Role getRole(int choiceRole) {
        if (choiceRole == 1) {
            return Role.TEACHER;
        } else {
            return Role.STUDENT;
        }
    }


    public void outAllByCourse(int courseId, Role role) {
        personRepository.getByCourseId(courseId, role);
    }

    public void delete(String lastName) {
        Person person = getByLastName(lastName);
        personRepository.remove(person);

    }

    public String lastNameIsValid() {
        String lastName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);
        Optional<Person> person = personRepository.getByLastName(lastName);
        if (person.isEmpty()) {
            throw new NoSuchPersonException(lastName);

        }
        return person.get().getLastName();
    }

    public Person getByLastName(String lastName) {
        Optional<Person> person = personRepository.getByLastName(lastName);
        if (person.isEmpty()) {
            throw new NoSuchPersonException(lastName);
        }
        return person.get();
    }

    public void sortByLastName() {
        personRepository.sortByLastName();
    }

    public void backupPerson(Role role) {
        personRepository.serializePerson(role);
    }

    public void deserialize(Role role) {
        personRepository.deserializePerson(role);
    }

    public void outputBeforeN() {
        personRepository.lastNameOfTeachersBeforeN();
    }

    public Boolean checkEmail(String email) {
        return personRepository.checkEmailForDuplicate(email);
    }
}

