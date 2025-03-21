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

    private String eventName;

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
    public List<Event> saveEvents(List<EventDto> eventsIn) {

        List<Event> events = new ArrayList<>();
        int eventCounter=0;
        for(EventDto e : eventsIn){
            Event eventToRegister = new Event();
            Tournament tournament =tournamentRepository.findTournamentByTournamentNameAndStartDateAndEndDate(e.getTournamentName(),e.getStartDate(),e.getEndDate());
            List<Test> test=testRepository.findTestByGenderAndStartAgeAndEndAgeAndStyleAndLength(e.getGender(),e.getStartAge(),e.getEndAge(),e.getStyle(),e.getLength());
            eventToRegister.setTournament(tournament);
            eventToRegister.setTest(test.getFirst());


            if(!Objects.equals(e.getStartAge(), e.getEndAge())) {
                eventName = (e.getLength()) + " " + e.getStyle() + "," + e.getStartAge() + "-" + e.getEndAge() + " "+"Años" + "," + e.getGender();
            }else{
                eventName = (e.getLength()) + " " + e.getStyle() + "," + e.getStartAge() + " "+"Años" + "," + e.getGender();
            }
            List<Event> eventRegistered=eventRepository.findEventByTournament(tournament);
            if(eventRegistered!=null){
                eventCounter++;
            }

            eventToRegister.setName(eventName);
            eventToRegister.setEventNumber(eventCounter);

            events.add(eventRepository.save(eventToRegister));



        }




        return events;
    }
}
