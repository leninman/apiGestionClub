package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findTestByDescription(String testName);

    List<Test> findTestByGenderAndStartAgeAndEndAgeAndStyleAndLength(String gender, Integer startAge, Integer endAge, String style, Integer length);

}
