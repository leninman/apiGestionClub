package com.sumadeportes.services;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Test;

import java.util.List;

public interface ITestService {
    List<Test> getAllTests();
    Test getTestById(Long testId);
    List<Test> getTestsByDescription(String description);
}
