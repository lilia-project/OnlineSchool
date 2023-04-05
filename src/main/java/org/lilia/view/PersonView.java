package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.PersonDto;
import org.lilia.log.Logger;
import org.lilia.log.LoggerFactory;
import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.service.CourseService;
import org.lilia.service.PersonService;

public class PersonView {

    private static final Logger logger = LoggerFactory.getLogger(PersonView.class);

    public PersonView(CourseService courseService) {
        this.courseService = courseService;
    }

    private final CourseService courseService;

    public void workWithPerson(PersonService personService) {
        logger.info("work with person section");

        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1 -> {
                    logger.info("selected to create person");
                    while (userChoice.equalsIgnoreCase("Y")) {

                        Person person = createNewPerson(personService);
                        logger.info("person created successful " + person.getRole() + " " + person.getLastName());

                        ConsoleUtils.print(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                }
                case 2 -> {
                    logger.info("selected get person by last name");
                    Person person = getPersonById(personService);

                    ConsoleUtils.print(Constants.ELEMENT_EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    editPerson(personService, userChoice, person);
                }
                case 3 -> {
                    logger.info("selected output person");
                    outputAll(personService);

                    ConsoleUtils.print(Constants.SORT_BY_LAST_NAME);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    if (userChoice.equalsIgnoreCase("Y")) {
                        personService.sortByLastName();
                    }
                }
                case 4 -> {
                    logger.info("selected delete person");
                    delete(personService);
                    logger.info("lecture deleted successful");
                }
                case 5 -> {
                    ConsoleUtils.print(Constants.ROLE);
                    Role role = personService.getRole(ConsoleUtils.readInteger());
                    personService.backupPerson(role);
                }
                case 6 -> {
                    ConsoleUtils.print(Constants.ROLE);
                    Role role = personService.getRole(ConsoleUtils.readInteger());
                    personService.deserialize(role);
                }
                case 7 -> {
                    logger.info("selected EXIT from menu");
                    ConsoleUtils.print(Constants.EXIT);
                }
                default -> {
                    logger.error(Constants.ERROR);
                    ConsoleUtils.print(Constants.ERROR);
                }
            }
            ConsoleUtils.print(Constants.STAY_IN);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private static Person createNewPerson(PersonService personService) {
        ConsoleUtils.print(Constants.NAME);
        String personName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

        ConsoleUtils.print(Constants.ROLE);
        int choiceRole = ConsoleUtils.readInteger();
        Role role = personService.getRole(choiceRole);

        return personService.createPerson(personName, role);
    }

    private Person getPersonById(PersonService personService) {
        ConsoleUtils.print(Constants.LAST_NAME);
        String personLastName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);
        Person person = personService.getByLastName(personLastName);
        print(person);
        return person;
    }

    private void editPerson(PersonService personService, String userChoice, Person person) {

        while (userChoice.equalsIgnoreCase("Y")) {

            ConsoleUtils.print(Constants.LAST_NAME);
            String personLastName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

            ConsoleUtils.print(Constants.NAME);
            String personName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

            ConsoleUtils.print(Constants.COURSE_ID);
            String courseId = String.valueOf(courseService.courseIdIsValid());

            ConsoleUtils.print(Constants.PHONE);
            String phone = ConsoleUtils.readAndValidationInput(Constants.NUMBER);

            ConsoleUtils.print(Constants.EMAIL);
            String email = ConsoleUtils.readAndValidationInput(Constants.NUMBER);

            PersonDto personDto = personService.createPersonDto(personLastName, personName, phone, email, Integer.parseInt(courseId));

            Person personUpdate = personService.updatePerson(person, personDto);
            System.out.println(personUpdate);

            ConsoleUtils.print(Constants.ELEMENT_EDIT);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private void outputAll(PersonService personService) {
        ConsoleUtils.print(Constants.COURSE_ID);
        int courseId = courseService.courseIdIsValid();

        ConsoleUtils.print(Constants.ROLE);
        int choiceRole = ConsoleUtils.readInteger();
        Role role = personService.getRole(choiceRole);

        personService.outAllByCourse(courseId, role);
    }

    private static void delete(PersonService personService) {
        ConsoleUtils.print(Constants.LAST_NAME);
        String lastName = personService.lastNameIsValid();
        personService.delete(lastName);
    }

    private void print(Person person) {
        System.out.println(person);
    }
}
