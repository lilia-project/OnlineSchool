package org.lilia;

import org.lilia.repository.*;
import org.lilia.service.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    public static Map<Class, Object> objectMap = new HashMap<>();

    static {
        DataBaseInitializer.createTables();

        DataBaseInitializer.fillTables();

        LectureRepository lectureRepository = new LectureRepository();
        HomeworkRepository homeworkRepository = new HomeworkRepository();
        HomeworkService homeworkService = new HomeworkService(homeworkRepository);
        AdditionalMaterialRepository additionalMaterialRepository = new AdditionalMaterialRepository();
        AdditionalMaterialService additionalMaterialService = new AdditionalMaterialService(additionalMaterialRepository);
        LectureService lectureService = new LectureService(lectureRepository, homeworkService);
        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository, lectureService);
        PersonRepository personRepository = new PersonRepository();
        PersonService personService = new PersonService(personRepository);
//        LectureView lectureView = new LectureView();
//        HomeworkView homeworkView = new HomeworkView(lectureService);
//        AdditionalMaterialView additionalMaterialView = new AdditionalMaterialView(lectureService);
//        CourseView courseView = new CourseView();
//        PersonView personView = new PersonView(courseService);
        ControlWorkService controlWorkService = new ControlWorkService();
//        ConfigurationReader configurationReader = new ConfigurationReader();

        objectMap.put(HomeworkService.class, homeworkService);
        objectMap.put(AdditionalMaterialService.class, additionalMaterialService);
        objectMap.put(LectureService.class, lectureService);
        objectMap.put(CourseService.class, courseService);
        objectMap.put(PersonService.class, personService);
        objectMap.put(ControlWorkService.class, controlWorkService);

    }

    public static <T> T get(Class<T> type) {
        return (T) objectMap.get(type);

    }
}
