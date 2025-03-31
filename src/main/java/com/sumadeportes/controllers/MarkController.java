package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.EventResponseDto;
import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.services.IEventService;
import com.sumadeportes.services.IMarkService;
import com.sumadeportes.services.ISwimmerService;
import com.sumadeportes.services.ITestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mark")
public class MarkController {
    private final IMarkService markService;
    private final ISwimmerService swimmerService;
    private final IEventService eventService;
    private final ITestService testService;

    public MarkController(IMarkService markService, ISwimmerService swimmerService, IEventService eventService, ITestService testService) {
        this.markService = markService;
        this.swimmerService = swimmerService;
        this.eventService = eventService;
        this.testService = testService;
    }

    @GetMapping("/getMark/{testId}")
    public ResponseEntity<respDto> getMarkByEvent(@RequestBody PersonId swimmerId, @PathVariable Long testId) {
        respDto response = new respDto();
        String mark = "";
        Optional<Swimmer> swimmer = swimmerService.getSwimmerById(swimmerId);
        Test test = testService.getTestById(testId);
        if (swimmer.isPresent()) {
            mark = markService.getMarkByEvent(swimmer.get(), test);
        }

        response.setMessage("Marks found");
        response.setCode("200");
        response.setData(mark);

        return ResponseEntity.ok(response);
    }
}


