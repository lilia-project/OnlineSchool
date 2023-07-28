package org.lilia.repository;

import org.lilia.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HomeworkRepo extends JpaRepository<Homework, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Homework homework SET homework.task = :#{#updatedHomework.task}, " +
            "homework.lectureId = :#{#updatedHomework.lectureId}," +
            "WHERE homework.id = :#{#updatedMaterial.id}")
    void updateHomework(Homework homework);

    @Transactional
    @Query("GET Homework homework WHERE homework.lectureId = lectureId")
    List<Homework> getByLectureId(Integer lectureId);

}
