package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.repositories.EventRepository;
import com.sumadeportes.model.repositories.TestRepository;
import com.sumadeportes.model.repositories.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class IEventServiceImpl implements IEventService {

    private final EventRepository eventRepository;

    private final TournamentRepository tournamentRepository;

    private final TestRepository testRepository;



    public IEventServiceImpl(EventRepository eventRepository, TournamentRepository tournamentRepository, TestRepository testRepository) {
        this.eventRepository = eventRepository;
        this.tournamentRepository = tournamentRepository;
        this.testRepository = testRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public List<Event> saveEvents(EventDto eventsIn) {

        List<Event> events = new ArrayList<>();
        int eventCounter=0;
        Tournament tournament =tournamentRepository.findTournamentByTournamentNameAndStartDateAndEndDate(eventsIn.getTournamentName(),eventsIn.getStartDate(),eventsIn.getEndDate());
        List<String> testsNames = eventsIn.getEventsNames();
        for(String testName : testsNames){
            Event eventToRegister = new Event();
            eventToRegister.setTournament(tournament);
            List<Test> test=testRepository.findTestByDescription(testName);
            eventToRegister.setTest(test.getFirst());
            eventToRegister.setName(testName);
            List<Event> eventRegistered=eventRepository.findEventByTournament(tournament);
            if(eventRegistered!=null){
                eventCounter++;
            }
            eventToRegister.setEventNumber(eventCounter);
            events.add(eventRepository.save(eventToRegister));
        }
        return events;
    }
}
