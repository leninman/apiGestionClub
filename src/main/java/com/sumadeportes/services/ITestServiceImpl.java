package com.sumadeportes.services;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.model.repositories.EventRepository;
import com.sumadeportes.model.repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITestServiceImpl implements ITestService {

    private final TestRepository testRepository;

    public ITestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    @Override
    public List<Test> getAllTests() {
        return (List<Test>) testRepository.findAll();
    }
}
