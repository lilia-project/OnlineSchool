package org.lilia.repository;

import org.lilia.entity.Person;
import org.lilia.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Person person SET person.lastName = :#{#updatedPerson.lastName}, " +
            "person.firstName = :#{#updatedPerson.firstName}," +
            "person.role = :#{#updatedPerson.role}," +
            "person.phone = :#{#updatedPerson.phone}," +
            "person.email = :#{#updatedPerson.email}," +
            "person.courseId = :#{#updatedPerson.courseId}" +
            "WHERE person.id = :#{#updatedPerson.id}")
    void updatePerson(Person person);

    @Transactional
    @Query("GET Person person WHERE person.courseId = courseId")
    List<Person> getByCourseId(Integer courseId);

    @Transactional
    @Query("GET Person person WHERE person.courseId = courseId AND person.role = role")
    List<Person> getByCourseIdAndRole(Integer courseId, Role role);

    @Transactional
    @Query("GET Person person WHERE person.lastName = lastName")
    Optional<Person> getByLastName(String lastName);

    @Transactional
    @Query("GET Person person ORDER BY person.lastName")
    List<String> sortByLastName();

    @Transactional
    @Query("GET Person person ORDER BY person.email")
    List<String> sortEmailsOfStudents();

    @Transactional
    @Query("GET Person person WHERE person.role = role")
    List<Person> getByRole(Role role);

    @Transactional
    @Query("GET Person person WHERE person.lastName < N")
    List<Person> printLastNameOfTeachersBeforeN();

    @Transactional
    @Query("GET Person person WHERE person.email = email DISTINCT person.email ")
    Boolean checkEmailForDuplicate(String email);

    @Transactional
    @Query("GET Person person WHERE person.email = email AND person.lastName = lastName")
    List<Person> printMapKeyEmailValueName();
}
