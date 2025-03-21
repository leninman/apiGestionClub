package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.UserEntity;
import com.sumadeportes.services.IEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "https://erika-github.github.io")
public class EventController {

    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<respDto> createEvents(@RequestBody List<EventDto> events) {
        respDto response = new respDto();
        try {
            List<Event> eventsSaved = eventService.saveEvents(events);
            response.setMessage("Events saved successfully");
            response.setCode("201");
            response.setData(eventsSaved);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage("Error saving events");
            response.setCode("500");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/getList")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
