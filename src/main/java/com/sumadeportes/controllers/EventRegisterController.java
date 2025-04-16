package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.dto.EventsMarksDto;
import com.sumadeportes.model.dto.RespDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.repositories.EventRepository;
import com.sumadeportes.model.repositories.SwimmerRepository;
import com.sumadeportes.model.repositories.TournamentRepository;
import com.sumadeportes.services.IEventsRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        eventRegisterService.saveEventRegister(eventRegisterDto);

        response.setMessage("Event register saved successfully");
        response.setCode("201");
        return ResponseEntity.status(201).body(response);




       /* try {
            for (EventsMarksDto eventMark : eventRegisterDto.getEventsMarks()) {
                // Obtener el ID del evento basado en el nombre del torneo y el nombre del evento
                Tournament tournament = tournamentRepository.findTournamentByName(eventMark.getTournamentName());
                Event event = eventRepository.findEventByNameAndTournament(eventMark.getEventName(), tournament);

                // Verificar si el nadador ya está inscrito en el evento
                boolean isRegistered = eventRegisterService.isSwimmerRegistered(event,swimmer.get());
                if (isRegistered) {
                    response.setMessage("Swimmer is already registered for the event: " + eventMark.getEventName());
                    response.setCode("409");
                    return ResponseEntity.status(409).body(response);
                }
            }



            // Call the service to save the event register
             eventRegisterService.saveEventRegister(eventRegisterDto);
            response.setMessage("Event register saved successfully");
            response.setCode("201");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.setMessage("Error saving event register");
            response.setCode("500");
            return ResponseEntity.status(500).body(response);
        }*/
    }
}
