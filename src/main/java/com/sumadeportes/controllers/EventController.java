package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.*;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.services.IEventService;
import com.sumadeportes.services.ITournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "https://erika-github.github.io")
public class EventController {

    private final IEventService eventService;

    private final ITournamentService tournamentService;

    public EventController(IEventService eventService, ITournamentService tournamentService) {
        this.eventService = eventService;
        this.tournamentService = tournamentService;
    }

    @PostMapping("/create")
    public ResponseEntity<RespDto> createEvents(@RequestBody EventDto events) {
        RespDto response = new RespDto();
        try {
            eventService.saveEvents(events);
            response.setMessage("Events saved successfully");
            response.setCode("201");
            //response.setData(eventsSaved);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage("Error saving events");
            response.setCode("500");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
//Programmed events
    @PostMapping("/programmed")
    public ResponseEntity<RespDto> getAllEventsByDate(@RequestBody EventsRequest eventsRequest) {
        RespDto respDto = new RespDto();
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Tournament> tournaments = tournamentService.gestTournamentsByMonth(eventsRequest.getMonth());
        if(tournaments.isEmpty()) {
            respDto.setCode("404");
            respDto.setMessage("No tournaments found");
            respDto.setData(null);
            return new ResponseEntity<>(respDto, HttpStatus.NOT_FOUND);
        }
        for (Tournament tournament : tournaments) {
            List<Event> events = eventService.getEventsByGenderAgeTournament(eventsRequest.getGender(), eventsRequest.getAge(), tournament.getId());
            List<String> eventName= new ArrayList<>();
            List<String> tournamentName=new ArrayList<>();
            for (Event event : events) {
                eventName.add(event.getName());
                tournamentName.add(event.getTournament().getTournamentName());
            }
            EventResponse eventResponse = new EventResponse(tournament.getStartDate().getDayOfMonth(), eventName, tournamentName,tournament.getEndDate().getDayOfMonth());
            eventResponses.add(eventResponse);
        }
        respDto.setCode("200");
        respDto.setMessage("Events found");
        respDto.setData(eventResponses);
        return ResponseEntity.ok(respDto);
    }

//All the events
    @PostMapping("/getList")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

//Events out of category
    @PostMapping("/outOfCategory")
    public ResponseEntity<RespDto> getOutOfCategoryEvents(@RequestBody EventsRequest eventsRequest) {
        RespDto respDto = new RespDto();
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Tournament> tournaments = tournamentService.gestTournamentsByMonth(eventsRequest.getMonth());
        if(tournaments.isEmpty()) {
            respDto.setCode("404");
            respDto.setMessage("No tournaments found");
            respDto.setData(null);
            return new ResponseEntity<>(respDto, HttpStatus.NOT_FOUND);
        }
        for (Tournament tournament : tournaments) {
            List<Event> events = eventService.getOutOfCategoryEvents(eventsRequest.getGender(), eventsRequest.getAge(), tournament.getId());
            List<String> eventName= new ArrayList<>();
            List<String> tournamentName=new ArrayList<>();
            for (Event event : events) {
                eventName.add(event.getName());
                tournamentName.add(event.getTournament().getTournamentName());
            }
            EventResponse eventResponse = new EventResponse(tournament.getStartDate().getDayOfMonth(), eventName, tournamentName,tournament.getEndDate().getDayOfMonth());
            eventResponses.add(eventResponse);
        }
        respDto.setCode("200");
        respDto.setMessage("Events found");
        respDto.setData(eventResponses);
        return ResponseEntity.ok(respDto);
    }
//Events ended
    @PostMapping("/finished")
    public ResponseEntity<RespDto> getAllEndedEvents(@RequestBody EventsRequest eventsRequest) {
        RespDto respDto = new RespDto();
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Tournament> tournaments = tournamentService.getFinishedTournamentsByMonth(eventsRequest.getMonth());
        if(tournaments.isEmpty()) {
            respDto.setCode("404");
            respDto.setMessage("No tournaments found");
            respDto.setData(null);
            return new ResponseEntity<>(respDto, HttpStatus.NOT_FOUND);
        }
        for (Tournament tournament : tournaments) {
            List<Event> events = eventService.getAllEndedEvents(eventsRequest.getGender(), eventsRequest.getAge(), tournament.getId());
            List<String> eventName= new ArrayList<>();
            List<String> tournamentName=new ArrayList<>();
            for (Event event : events) {
                eventName.add(event.getName());
                tournamentName.add(event.getTournament().getTournamentName());
            }
            EventResponse eventResponse = new EventResponse(tournament.getStartDate().getDayOfMonth(), eventName, tournamentName,tournament.getEndDate().getDayOfMonth());
            eventResponses.add(eventResponse);
        }
        respDto.setCode("200");
        respDto.setMessage("Events found");
        respDto.setData(eventResponses);
        return ResponseEntity.ok(respDto);
    }
}


