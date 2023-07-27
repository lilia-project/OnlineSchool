package org.lilia.service;

import org.lilia.constant.Constants;
import org.lilia.dto.PersonDto;
import org.lilia.entity.Person;
import org.lilia.entity.Role;
import org.lilia.exception.NoSuchPersonException;
import org.lilia.repository.PersonRepo;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    private static FilePath getPath(Role role) {

        FilePath filePath;
        if (role.getField().equals("TEACHER")) {
            filePath = FilePath.FILE_PATH_TEACHER;
        } else {
            filePath = FilePath.FILE_PATH_STUDENT;
        }
        return filePath;
    }

    public void createNewPerson(Role role, String lastName) {
        if (lastName == null | role == null) {
            throw new IllegalArgumentException("last name or role is null");
        }
        Person person = new Person();
        person.setRole(role);
        person.setLastName(lastName);
        personRepo.save(person);
    }

    public PersonDto createPersonDto(String lastName, String firstName, String phone, String email, int courseId) {
        return new PersonDto(lastName, firstName, phone, email, courseId);
    }

    public void updatePerson(Person person, PersonDto personDto) {
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
        personRepo.updatePerson(person);
    }

    public Role getRole(int choiceRole) {
        if (choiceRole == 1) {
            return Role.TEACHER;
        } else {
            return Role.STUDENT;
        }
    }

    public Optional<List<Person>> outAllByCourseId(int courseId, Role role) {
        return Optional.ofNullable(personRepo.getByCourseIdAndRole(courseId, role));
    }

    public void delete(String lastName) {
        Person person = new Person();
        person.setLastName(lastName);
        personRepo.delete(person);
    }

    public String lastNameIsValid() {
        String lastName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);
        return getByLastName(lastName).getLastName();
    }

    public Person getByLastName(String lastName) {
        Optional<Person> person = personRepo.getByLastName(lastName);
        if (person.isEmpty()) {
            throw new NoSuchPersonException(lastName);
        }
        return person.get();
    }

    public List<String> sortByLastName() {
        return personRepo.sortByLastName();
    }

    public void backupPerson(Role role) {

        List<Person> personList = personRepo.getByRole(role);
        FilePath filePath = getPath(role);
        Serializer.serialize(personList, filePath);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserialize(Role role) {

        FilePath filePath = getPath(role);
        Object deserialize = Serializer.deserialize(filePath.getPath());
        List<Person> people = (List<Person>) deserialize;
        for (Person person : people) {
            personRepo.save(person);
        }
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
    }

    public List<Person> outputBeforeN() {
        return personRepo.printLastNameOfTeachersBeforeN();
    }

    public Boolean checkEmail(String email) {
        return personRepo.checkEmailForDuplicate(email);
    }

    public List<Person> printMap() {
        return personRepo.printMapKeyEmailValueName();
    }

    public void printEmailsOfStudentsToFile() {
        Path path = Paths.get(FilePath.FILE_PATH_EMAILS_OF_STUDENTS.getPath());
        List<String> emailsOfStudents = personRepo.sortEmailsOfStudents();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(emailsOfStudents.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

