package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.PersonDto;
import org.lilia.exception.NoSuchPersonException;
import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.repository.PersonRepository;
import org.lilia.serialization.FilePath;
import org.lilia.util.ConsoleUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void createNewPerson(Role role, String lastName) {
        if (lastName == null | role == null) {
            throw new IllegalArgumentException("last name or role is null");
        }
        personRepository.insertValue(role, lastName);
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
//        Person person = getByLastName(lastName);
        PersonRepository.remove(lastName);

    }

    public String lastNameIsValid() {
        String lastName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);
        Optional<Person> person = PersonRepository.getByLastName(lastName);
        if (person.isEmpty()) {
            throw new NoSuchPersonException(lastName);

        }
        return person.get().getLastName();
    }

    public Person getByLastName(String lastName) {
        Optional<Person> person = PersonRepository.getByLastName(lastName);
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
        personRepository.printLastNameOfTeachersBeforeN();
    }

    public Boolean checkEmail(String email) {
        return personRepository.checkEmailForDuplicate(email);
    }

    public void printMap() {
        personRepository.printMapKeyEmailValueName();
    }

    public void printEmailsOfStudentsToFile() {
        Path path = Paths.get(FilePath.FILE_PATH_EMAILS_OF_STUDENTS.getPath());
        List<String> emailsOfStudents = personRepository.sortEmailsOfStudents();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(emailsOfStudents.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

