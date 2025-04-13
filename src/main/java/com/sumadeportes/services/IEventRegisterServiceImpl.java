package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.dto.EventsMarksDto;
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
    public List<com.sumadeportes.model.entities.EventRegister> saveEventRegister(EventRegisterDto eventRegisterDto) {

        long swimmerNumber = 0L;
        Integer teamNumber = 0;
        Team team = new Team();
        List<com.sumadeportes.model.entities.EventRegister> eventsRegisteredSaved = new ArrayList<>();

        Optional<Swimmer> swimmer = swimmerService.getSwimmerById(new PersonId(eventRegisterDto.getSwimmerDocumentType(), eventRegisterDto.getSwimmerDocumentNumber()));
        if (swimmer.isPresent()) {
            team = swimmer.get().getTeam();
        }

        for (EventsMarksDto eventMark : eventRegisterDto.getEventsMarks()) {
            String tournamentName = eventMark.getTournamentName();
            Tournament tournamentSaved = tournamentRepository.findTournamentByName(tournamentName);
            Event event = eventRepository.findEventByNameAndTournament(eventMark.getEventName(), tournamentSaved);

            TournamentTeam tournamentTeam = tournamentTeamRepository.findTournamentTeamByTournamentAndTeam(tournamentSaved, team);
            teamNumber = tournamentTeam.getTeamPosition();

            com.sumadeportes.model.entities.EventRegister eventRegisterToSave = new com.sumadeportes.model.entities.EventRegister();
            swimmer.ifPresent(eventRegisterToSave::setSwimmer);
            eventRegisterToSave.setEvent(event);
            List<com.sumadeportes.model.entities.EventRegister> eventsRegistered = eventRegisterRepository.findEventRegisterByEvent(event);
            if (!eventsRegistered.isEmpty()) {
                List<Integer> swimmerNumbers = new ArrayList<>();
                for (com.sumadeportes.model.entities.EventRegister eventRegistered : eventsRegistered) {
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
            com.sumadeportes.model.entities.EventRegister eventregisterSaved = eventRegisterRepository.save(eventRegisterToSave);
            eventsRegisteredSaved.add(eventregisterSaved);
        }

        return eventsRegisteredSaved;
    }

    @Override
    public boolean isSwimmerRegistered(Event event, Swimmer swimmer) {
        return eventRegisterRepository.existsByEventAndSwimmer(event, swimmer);
    }

    @Override
    public List<com.sumadeportes.model.entities.EventRegister> getAllEventRegisters() {
        return List.of();
    }

    @Override
    public com.sumadeportes.model.entities.EventRegister getEventRegisterById(Long id) {
        return null;
    }

    @Override
    public void deleteEventRegister(Long id) {

    }


}
