package org.lilia.view;

import org.lilia.ConsoleUtils;
import org.lilia.Constants;
import org.lilia.dto.CourseDto;
import org.lilia.model.Course;
import org.lilia.service.CourseService;

public class CourseView {

    public void workWithCourse(CourseService courseService) {

        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            switch (ConsoleUtils.choiceAction()) {
                case 1 -> {
                    String userChoice1 = userChoice;
                    while (userChoice1.equalsIgnoreCase("Y")) {

                        ConsoleUtils.print(Constants.NAME);
                        String courseName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        courseService.createNewCourse(courseName);

                        ConsoleUtils.print(Constants.CREATE_NEW);
                        userChoice1 = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                }
                case 2 -> {
                    Course course = getCourseById(courseService);
                    ConsoleUtils.print(Constants.ELEMENT_EDIT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    editCourse(courseService, userChoice, course);
                }
                case 3 -> {
                    courseService.outputAll();
                    ConsoleUtils.print(Constants.SORT_BY_NAME);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    if (userChoice.equalsIgnoreCase("Y")) {
                        courseService.sortByName();
                    }
                }
                case 4 -> deleteCourse(courseService);
                case 5 -> ConsoleUtils.print(Constants.EXIT);
                default -> ConsoleUtils.print(Constants.ERROR);
            }
            ConsoleUtils.print(Constants.STAY_IN);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }


    private static void deleteCourse(CourseService courseService) {
        int courseId;
        ConsoleUtils.print(Constants.COURSE_ID);
        courseId = courseService.courseIdIsValid();
        courseService.deleteById(courseId);
    }

    private static void editCourse(CourseService courseService, String userChoice, Course course) {
        while (userChoice.equalsIgnoreCase("Y")) {

            ConsoleUtils.print(Constants.NAME);
            String courseName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

            CourseDto courseDto = courseService.createCourseDto(courseName);

            Course courseUpdate = courseService.updateCourse(course, courseDto);
            System.out.println(courseUpdate);

            ConsoleUtils.print(Constants.ELEMENT_EDIT);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
        }
    }

    private static Course getCourseById(CourseService courseService) {
        ConsoleUtils.print(Constants.COURSE_ID);
        int courseId = ConsoleUtils.readInteger();

        Course course = courseService.getRequireById(courseId);
        courseService.print(course);
        return course;
    }

}
