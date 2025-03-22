package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.services.ITestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "https://erika-github.github.io")
public class TestController {

    private final ITestService testService;

    public TestController(ITestService testService) {
        this.testService = testService;
    }

    @RequestMapping("/getList")
    public ResponseEntity<respDto> getAllTests() {
        respDto respDto = new respDto();
        List<Test> tests=testService.getAllTests();
        respDto.setData(tests);
        return ResponseEntity.ok(respDto);
    }
}
