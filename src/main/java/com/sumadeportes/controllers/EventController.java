package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.dto.EventResponseDto;
import com.sumadeportes.model.dto.EventsResponse;
import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.services.IEventService;
import com.sumadeportes.services.ITournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<respDto> createEvents(@RequestBody EventDto events) {
        respDto response = new respDto();
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

    @PostMapping("/getListByDate")
    // public ResponseEntity<respDto> getAllEventsByDate(@RequestParam LocalDate date,String gender,Integer age) {
    public ResponseEntity<respDto> getAllEventsByDate(@RequestParam int month, String gender, Integer age) {
        respDto respDto = new respDto();
        List<Event> eventsList = new ArrayList<>();
        Map<Integer, EventsResponse> eventsResponseMap = new HashMap<>();

        List<Tournament> tournaments = tournamentService.gestTournamentsByMonth(month);
        for (Tournament tournament : tournaments) {
            List<Event> events = eventService.getEventsByGenderAgeTournament(gender, age, tournament.getTournamentName());
            eventsList.addAll(events);
        }

        for (Event event : eventsList) {
            LocalDate startDate = event.getTournament().getStartDate();
            LocalDate endDate = event.getTournament().getEndDate();
            while (!startDate.isAfter(endDate)) {
                int dayOfMonth = startDate.getDayOfMonth();
                EventsResponse eventsResponse = eventsResponseMap.getOrDefault(dayOfMonth, new EventsResponse(dayOfMonth, new ArrayList<>(), new ArrayList<>()));
                eventsResponse.getEventName().add(event.getName());
                eventsResponse.getTournamentsName().add(event.getTournament().getTournamentName());
                eventsResponseMap.put(dayOfMonth, eventsResponse);
                startDate = startDate.plusDays(1);
            }
        }
        respDto.setCode("200");
        respDto.setMessage("Events found");

        respDto.setData(new ArrayList<>(eventsResponseMap.values()));
        return ResponseEntity.ok(respDto);


    }


    @PostMapping("/getList")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/getOuOfCategoryEvents")
    public ResponseEntity<respDto> getOufOfCategoryEvents(String gender, Integer age) {
        respDto response = new respDto();
        try {
            List<Event> endedEvents = eventService.getOutOfCategoryEvents(gender, age);
            response.setMessage("Out of category events retrieved successfully");
            response.setCode("200");
            response.setData(endedEvents);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error retrieving out of category events");
            response.setCode("500");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


