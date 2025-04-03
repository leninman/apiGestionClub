package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.dto.EventsMarks;
import com.sumadeportes.model.entities.*;
import com.sumadeportes.model.repositories.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IEventRegisterServiceImpl implements IEventsRegisterService{

    private final EventRepository eventRepository;
    private final ISwimmerService swimmerService;
    private final EventRegisterRepository eventRegisterRepository;
    private final TournamentTeamRepository tournamentTeamRepository;
    private final MarkRepository markRepository;
    private final TournamentRepository tournamentRepository;

    public IEventRegisterServiceImpl(EventRepository eventRepository, ISwimmerService swimmerService, EventRegisterRepository eventRegisterRepository, TournamentTeamRepository tournamentTeamRepository, MarkRepository markRepository, TournamentRepository tournamentRepository) {
        this.eventRepository = eventRepository;
        this.swimmerService = swimmerService;
        this.eventRegisterRepository = eventRegisterRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.markRepository = markRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<EventRegister> saveEventRegister(EventRegisterDto eventRegisterDto) {

        long swimmerNumber = 0L;
        Integer teamNumber = 0;
        Team team = new Team();
        List<EventRegister> eventsRegisteredSaved = new ArrayList<>();

        Optional<Swimmer> swimmer = swimmerService.getSwimmerById(new PersonId(eventRegisterDto.getSwimmerDocumentType(), eventRegisterDto.getSwimmerDocumentNumber()));
        if (swimmer.isPresent()) {
            team = swimmer.get().getTeam();
        }

        for (EventsMarks eventMark : eventRegisterDto.getEventsMarks()) {
            String tournamentName = eventMark.getTournamentName();
            Tournament tournamentSaved = tournamentRepository.findTournamentByName(tournamentName);
            Event event = eventRepository.findEventByNameAndTournament(eventMark.getEventName(), tournamentSaved);

            TournamentTeam tournamentTeam = tournamentTeamRepository.findTournamentTeamByTournamentAndTeam(tournamentSaved, team);
            teamNumber = tournamentTeam.getTeamPosition();

            EventRegister eventRegisterToSave = new EventRegister();
            swimmer.ifPresent(eventRegisterToSave::setSwimmer);
            eventRegisterToSave.setEvent(event);
            List<EventRegister> eventsRegistered = eventRegisterRepository.findEventRegisterByEvent(event);
            if (!eventsRegistered.isEmpty()) {
                List<Integer> swimmerNumbers = new ArrayList<>();
                for (EventRegister eventRegistered : eventsRegistered) {
                    String swimmerNumberString = eventRegistered.getSwimmerNumber();
                    String stringPart = swimmerNumberString.substring(1);
                    Integer partInt = Integer.parseInt(stringPart);
                    swimmerNumbers.add(partInt);
                }
                Long biggestValue = Long.valueOf(Collections.max(swimmerNumbers));
                swimmerNumber = biggestValue + 1;
            } else {
                swimmerNumber = 1L;
            }

            String swimmerNumberFormatted = String.format("%04d", swimmerNumber);
            String concat = teamNumber + swimmerNumberFormatted;
            eventRegisterToSave.setSwimmerNumber(concat);

            // Establecer la marca para cada evento
            eventRegisterToSave.setMark(String.valueOf(eventMark.getMark()));
            EventRegister eventregisterSaved = eventRegisterRepository.save(eventRegisterToSave);
            eventsRegisteredSaved.add(eventregisterSaved);
        }

        return eventsRegisteredSaved;
    }

    @Override
    public List<EventRegister> getAllEventRegisters() {
        return List.of();
    }

    @Override
    public EventRegister getEventRegisterById(Long id) {
        return null;
    }

    @Override
    public void deleteEventRegister(Long id) {

    }


}
