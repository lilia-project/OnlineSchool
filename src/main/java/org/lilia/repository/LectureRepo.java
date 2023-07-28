package org.lilia.repository;

import org.lilia.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LectureRepo extends JpaRepository<Lecture, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Lecture lecture SET lecture.name = :#{#updatedLecture.name}, " +
            " lecture.lectureDate = :#{#updatedLecture.lectureDate} " +
            " lecture.courseId = :#{#updatedLecture.courseId} " +
            " lecture.personId = :#{#updatedLecture.personId} " +
            " lecture.description = :#{#updatedLecture.description} " +
            "WHERE lecture.id = :#{#updatedLecture.id}")
    void updateLecture(Lecture lecture);

    @Transactional
    @Query("GET Lecture lecture WHERE lecture.courseId = courseId")
    List<Lecture> getByCourseId(Integer courseId);

    @Transactional
    @Query("GET Lecture lecture ORDER BY createAt")
    List<Lecture> getLectureByEarlyTime();

    @Transactional
    @Query("GET Lecture lecture WHERE lecture.localDate < localDate")
    List<Lecture> isBeforeDate(LocalDateTime localDate);

    @Transactional
    @Query("GET Lecture lecture WHERE lecture.localDate > localDate")
    List<Lecture> isAfterDate(LocalDateTime localDate);

    @Transactional
    @Query("GET Lecture lecture WHERE lecture.localDate > localDate AND lecture.localDate < localDateSecond")
    List<Lecture> isBetweenDate(LocalDateTime localDate, LocalDateTime localDateSecond);


    @Transactional
    @Query("GET Lecture lecture GROUP BY person.getPerson")
    List<Lecture> getLecturesGroupingByTeacher();

}