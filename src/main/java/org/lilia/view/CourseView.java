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
                case 1:
                    while (userChoice.equalsIgnoreCase("Y")) {

                        ConsoleUtils.print(Constants.NAME);
                        String courseName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        courseService.createCourse(courseName);

                        System.out.println(Constants.CREATE_NEW);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 2:
                    ConsoleUtils.print(Constants.COURSE_ID);
                    int courseId = ConsoleUtils.readInteger();

                    Course course = courseService.getRequireById(courseId);
                    courseService.print(course);
                    ConsoleUtils.print(Constants.EDIT_ELEMENT);
                    userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);

                    while (userChoice.equalsIgnoreCase("Y")) {

                        ConsoleUtils.print(Constants.NAME);
                        String courseName = ConsoleUtils.readAndValidationInput(Constants.NAME_OR_DESCRIPTION);

                        CourseDto courseDto = courseService.createCourseDto(courseName);

                        Course courseUpdate = courseService.updateCourse(course, courseDto);
                        System.out.println(courseUpdate);

                        ConsoleUtils.print(Constants.EDIT_ELEMENT);
                        userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
                    }
                    break;
                case 3:
                    courseService.out();
                    break;
                case 4:
                    ConsoleUtils.print(Constants.COURSE_ID);
                    courseId = courseService.courseIdIsValid();
                    courseService.deleteById(courseId);
                    break;
                case 5:
                    ConsoleUtils.print(Constants.EXIT);
                    break;
                default:
                    ConsoleUtils.print(Constants.ERROR);
                    break;
            }
            ConsoleUtils.print(Constants.STAY_IN);
            userChoice = ConsoleUtils.readAndValidationInput(Constants.YES_OR_NO);
            if (userChoice.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

}
