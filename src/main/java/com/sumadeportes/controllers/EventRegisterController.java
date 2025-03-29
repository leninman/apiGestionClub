package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.services.IEventsRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventsregister")
@CrossOrigin(origins = "https://erika-github.github.io")
public class EventRegisterController {

     private final IEventsRegisterService eventRegisterService;

    public EventRegisterController(IEventsRegisterService eventRegisterService) {
        this.eventRegisterService = eventRegisterService;
    }
    @PostMapping("/create")
    public ResponseEntity<respDto> saveEventRegister(@RequestBody EventRegisterDto eventRegisterDto) {
        respDto response = new respDto();
        try {
            // Call the service to save the event register
             eventRegisterService.saveEventRegister(eventRegisterDto);
            response.setMessage("Event register saved successfully");
            response.setCode("201");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.setMessage("Error saving event register");
            response.setCode("500");
            return ResponseEntity.status(500).body(response);
        }
    }
}
