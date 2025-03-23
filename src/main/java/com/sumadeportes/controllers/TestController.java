package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.services.ITestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tests")
@CrossOrigin(origins = "https://erika-github.github.io")
public class TestController {

    private final ITestService testService;

    public TestController(ITestService testService) {
        this.testService = testService;
    }

    @GetMapping("/getList")
    public ResponseEntity<respDto> getAllTests() {
        respDto respDto = new respDto();
        List<Test> tests=testService.getAllTests();
        respDto.setMessage("Tests found");
        respDto.setCode("200");
        List<String> testsNames = tests.stream().map(Test::getDescription).toList();
        respDto.setData(testsNames);
        return ResponseEntity.ok(respDto);
    }
}
