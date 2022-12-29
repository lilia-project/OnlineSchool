package org.lilia.service;


import org.lilia.models.Model;
import org.lilia.models.Person;
import org.lilia.repository.TeacherRepository;

public class PersonService {
    private final TeacherRepository teacherRepository;

    public PersonService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;

    }

    public Person createPerson(int courseId, String name) {
        Person teacher = Person.createTeacher(courseId, name);
        teacherRepository.add(teacher);
        System.out.println("from PersonService \nTeacher has been created" + teacher);
        return teacher;
    }

    public int[] getAllTeacherIds() {
        Model[] person = teacherRepository.getAll();
        int[] allTeacherIds = new int[person.length];
        for (int i = 0; i < person.length; i++) {
           // allTeacherIds[i] = person.getId;
        }
        return allTeacherIds;
    }
}
