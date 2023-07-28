package org.lilia.repository;

import org.lilia.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Course course SET course.name = :#{#updatedCourse.name} " +
            "WHERE course.id = :#{#updatedCourse.id}")
    void updateCourse(Course course);

    @Transactional
    @Query("GET Course course ORDER BY name")
    List<Course> sortByName();

}
