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
import java.util.List;

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
    @GetMapping("/getListByDate")
    public ResponseEntity<respDto> getAllEventsByDate(@RequestParam LocalDate date,String gender,Integer age) {

        respDto respDto = new respDto();
        List<Event> eventsList=new ArrayList<>();
      //  List<EventResponseDto> eventsResponsesDtos=new ArrayList<>();
        List<EventsResponse> eventsResponses=new ArrayList<>();
        List<String> tournamentsName=new ArrayList<>();
        List<String> eventName=new ArrayList<>();



        List<Tournament> tournaments = tournamentService.getTournamentsByDate(date);
        for (Tournament tournament : tournaments) {
            List<Event> events=eventService.getEventsByGenderAgeTournament(gender,age,tournament.getTournamentName());
            eventsList.addAll(events);
        }

        for (Event event : eventsList) {
            tournamentsName.add(event.getTournament().getTournamentName());
            eventName.add(event.getName());
            EventsResponse eventsResponse=new EventsResponse();
            eventsResponse.setDate(date.getDayOfMonth());
            eventsResponse.setEventName(eventName);
            eventsResponse.setTournamentsName(tournamentsName);
            eventsResponses.add(eventsResponse);




           // EventResponseDto eventResponseDto = new EventResponseDto();
           // eventResponseDto.setId(event.getId());
          //  eventResponseDto.setEventName(event.getName());
         //   eventResponseDto.setEventNumber(event.getEventNumber());
         //   eventResponseDto.setTournamentName(event.getTournament().getTournamentName());
         //   eventResponseDto.setStartDate(event.getTournament().getStartDate());
       //     eventResponseDto.setEndDate(event.getTournament().getEndDate());
        //    eventResponseDto.setGender(event.getTest().getGender());
       //     eventResponseDto.setStyle(event.getTest().getStyle());
         //   eventResponseDto.setLength(event.getTest().getLength());
       //     eventResponseDto.setStartAge(event.getTest().getStartAge());
        //    eventResponseDto.setEndAge(event.getTest().getEndAge());
       //     eventsResponsesDtos.add(eventResponseDto);




        }
        respDto.setData(eventsResponses);
        return ResponseEntity.ok(respDto);

    }


    @GetMapping("/getList")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
