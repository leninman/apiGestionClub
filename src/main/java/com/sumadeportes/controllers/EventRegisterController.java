package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.dto.EventsMarksDto;
import com.sumadeportes.model.dto.RespDto;
import com.sumadeportes.model.entities.*;
import com.sumadeportes.model.repositories.EventRepository;
import com.sumadeportes.model.repositories.SwimmerRepository;
import com.sumadeportes.model.repositories.TournamentRepository;
import com.sumadeportes.services.IEventsRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventsregister")
@CrossOrigin(origins = "https://erika-github.github.io")
public class EventRegisterController {

     private final IEventsRegisterService eventRegisterService;
    private final TournamentRepository tournamentRepository;
    private final EventRepository eventRepository;
    private final SwimmerRepository swimmerRepository;

    public EventRegisterController(IEventsRegisterService eventRegisterService, TournamentRepository tournamentRepository, EventRepository eventRepository, SwimmerRepository swimmerRepository) {
        this.eventRegisterService = eventRegisterService;
        this.tournamentRepository = tournamentRepository;
        this.eventRepository = eventRepository;
        this.swimmerRepository = swimmerRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<RespDto> saveEventRegister(@RequestBody EventRegisterDto eventRegisterDto) {
        RespDto response = new RespDto();
        Swimmer swimmer = swimmerRepository.findSwimmerBySwimmerId(new PersonId(eventRegisterDto.getSwimmerDocumentType(), eventRegisterDto.getSwimmerDocumentNumber()));
        if(swimmer==null) {
            response.setMessage("Swimmer not found");
            response.setCode("404");
            response.setData(new ArrayList<>());
            return ResponseEntity.status(404).body(response);
        }
        List<EventRegister> eventsRegister=eventRegisterService.saveEventRegister(eventRegisterDto);
        if(eventsRegister==null) {
            response.setMessage("Swimmer is already registered in this event");
            response.setCode("409");
            response.setData(new ArrayList<>());
            return ResponseEntity.status(409).body(response);
        }
        response.setMessage("Event register saved successfully");
        response.setCode("201");
        return ResponseEntity.status(201).body(response);
    }
}
