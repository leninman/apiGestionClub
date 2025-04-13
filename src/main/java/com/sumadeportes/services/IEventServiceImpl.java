package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.repositories.EventRepository;
import com.sumadeportes.model.repositories.TestRepository;
import com.sumadeportes.model.repositories.TournamentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



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
    public void saveEvents(EventDto eventsIn) {
        Integer eventCounter = 1;
        String tournamentName = eventsIn.getTournamentName();
        String keyWord=" desde";
        int ind=tournamentName.indexOf(keyWord);
        if(ind!=-1){
            tournamentName=tournamentName.substring(0,ind);
        }
        Tournament tournament = tournamentRepository.findTournamentByTournamentNameAndStartDateAndEndDate(
                tournamentName, eventsIn.getStartDate(), eventsIn.getEndDate());
        List<Event> tournamentsEvents = eventRepository.findEventByTournament(tournament);

        if (!tournamentsEvents.isEmpty()) {
            eventCounter = tournamentsEvents.stream()
                    .map(Event::getEventNumber)
                    .max(Integer::compare)
                    .orElse(0) + 1; // Incrementar el valor máximo encontrado
        }

        List<String> testsNames = eventsIn.getEventsNames();
        for (String testName : testsNames) {
            Event eventToRegister = new Event();
            eventToRegister.setTournament(tournament);
            Test test = testRepository.findTestByDescription(testName).getFirst();
            eventToRegister.setTest(test);
            eventToRegister.setName(testName);
            eventToRegister.setEventNumber(eventCounter++);
            eventRepository.save(eventToRegister);
        }
    }

    @Override
    public List<Event> getEventsByTournament(Tournament tournament) {
        return eventRepository.findEventByTournament(tournament);
    }

    @Override
    public List<Event> getEventsByGenderAgeTournament(String gender,Integer age,Long tournamentId) {
        return eventRepository.findAllWithTournamentAndTeams(gender,age,tournamentId);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> getOutOfCategoryEvents(String gender, int age, Long tournamentId) {
        //List<Event> outOfCategoryEvents = eventRepository.findAllEventsNotInCategory(gender, age);
        return eventRepository.findAllEventsNotInCategory(gender, age, tournamentId);
    }

    @Override
    public List<Event> getAllEndedEvents(String gender, Integer age, Long tournamentId) {
        return eventRepository.findAllWithTournamentAndTeamsEnded(gender,age,tournamentId);
    }
}
