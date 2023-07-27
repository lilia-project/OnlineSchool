package org.lilia.repository;

import org.lilia.entity.AdditionalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdditionalMaterialRepo extends JpaRepository<AdditionalMaterial, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE AdditionMaterial material SET material.name = :#{#updatedMaterial.name}, " +
            "material.lectureId = :#{#updatedMaterial.lectureId}," +
            " material.resourceType = :#{#updatedMaterial.resourceType} " +
            "WHERE material.id = :#{#updatedMaterial.id}")
    void updateMaterial(AdditionalMaterial updatedMaterial);

    @Transactional
    @Query("GET AdditionMaterial material WHERE material.lectureId = lectureId")
    Optional<List<AdditionalMaterial>> getByLectureId(Integer lectureId);

    @Transactional
    @Query("GET AdditionMaterial material WHERE material.AdditionalMaterial.SortField = sortField AND material.lectureId = lectureId")
    List<AdditionalMaterial> getAllBySortFieldAndLectureId(AdditionalMaterial.SortField sortField, int lectureId);

    @Transactional
    @Query("GET AdditionMaterial material GROUP BY lectureId")
    List<AdditionalMaterial> printAddMaterialsGroupingByLectureId();

}
