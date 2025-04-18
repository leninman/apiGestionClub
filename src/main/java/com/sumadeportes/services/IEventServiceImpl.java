package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.entities.*;
import com.sumadeportes.model.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



@Service
public class IEventServiceImpl implements IEventService {

    private final EventRepository eventRepository;

    private final TournamentRepository tournamentRepository;

    private final TestRepository testRepository;

    private final EventRegisterRepository eventRegisterRepository;

    private final SwimmerRepository swimmerRepository;



    public IEventServiceImpl(EventRepository eventRepository, TournamentRepository tournamentRepository, TestRepository testRepository, EventRegisterRepository eventRegisterRepository, SwimmerRepository swimmerRepository) {
        this.eventRepository = eventRepository;
        this.tournamentRepository = tournamentRepository;
        this.testRepository = testRepository;
        this.eventRegisterRepository = eventRegisterRepository;
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public List<Event> saveEvents(EventDto eventsIn) {
        int eventCounter = 1;
        String tournamentName = eventsIn.getTournamentName();
        String keyWord = " desde";
        int ind = tournamentName.indexOf(keyWord);
        List<Event> eventsRegistered = new ArrayList<>();

        if (ind != -1) {
            tournamentName = tournamentName.substring(0, ind);
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

        // Verificar si algún evento ya existe
        boolean anyEventExists = eventsIn.getEventsNames().stream()
                .anyMatch(testName -> eventRepository.findEventByNameAndTournament(testName, tournament) != null);

        if (anyEventExists) {
            return null; // Retornar null si algún evento ya existe
        }

        // Guardar los eventos
        List<String> testsNames = eventsIn.getEventsNames();
        for (String testName : testsNames) {
            Event eventToRegister = new Event();
            eventToRegister.setTournament(tournament);

            // Validar si el test existe
            Test test = testRepository.findTestByDescription(testName)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Test no encontrado: " + testName));

            eventToRegister.setTest(test);
            eventToRegister.setName(testName);
            eventToRegister.setEventNumber(eventCounter++);
            eventToRegister.setStartDate(eventsIn.getStartDate());
            eventToRegister.setEndDate(eventsIn.getEndDate());
            eventRepository.save(eventToRegister);
            eventsRegistered.add(eventToRegister);
        }

        return eventsRegistered;
    }



    @Override
    public List<Event> getEventsByGenderAgeTournament(String gender,Integer age,Long tournamentId) {
        List<Event> events = eventRepository.findAllWithTournamentAndTeams(gender,age,tournamentId);
        List<Event> eventsOrdered = events.stream()
                .sorted(Comparator.comparing(event -> event.getStartDate().getDayOfMonth()))
                .toList();

        return eventsOrdered;
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
