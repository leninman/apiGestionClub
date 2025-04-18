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
    private final TournamentRepository tournamentRepository;


    public IEventRegisterServiceImpl(EventRepository eventRepository, ISwimmerService swimmerService, EventRegisterRepository eventRegisterRepository, TournamentTeamRepository tournamentTeamRepository, TournamentRepository tournamentRepository) {
        this.eventRepository = eventRepository;
        this.swimmerService = swimmerService;
        this.eventRegisterRepository = eventRegisterRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<EventRegister> saveEventRegister(EventRegisterDto eventRegisterDto) {
        long swimmerNumber;
        Integer teamNumber;
        Team team;
        List<EventRegister> eventsRegisterSaved = new ArrayList<>();
        Swimmer swimmer = swimmerService.getSwimmerById(new PersonId(eventRegisterDto.getSwimmerDocumentType(), eventRegisterDto.getSwimmerDocumentNumber()));
        team = swimmer.getTeam();
        for(EventsMarksDto eventsMarksDto:eventRegisterDto.getEventsMarks()){
            Tournament tournament = tournamentRepository.findTournamentByTournamentNameAndStartDateAndEndDate(eventsMarksDto.getTournamentName(), eventsMarksDto.getStartDate(), eventsMarksDto.getEndDate());
            Event event = eventRepository.findEventByNameAndTournament(eventsMarksDto.getEventName(), tournament);
                if (event != null) {
                    //Validamos si ya el nadador esta inscrito en ese evento en ese torneo
                    EventRegister eventRegister = eventRegisterRepository.findEventRegisterByEventAndSwimmer(event, swimmer);
                    if(eventRegister!= null) {
                        return null;
                    }
                }
        }
        for(EventsMarksDto eventsMarksDto : eventRegisterDto.getEventsMarks()){
            Tournament tournament = tournamentRepository.findTournamentByTournamentNameAndStartDateAndEndDate(eventsMarksDto.getTournamentName(), eventsMarksDto.getStartDate(), eventsMarksDto.getEndDate());
            TournamentTeam tournamentTeam = tournamentTeamRepository.findTournamentTeamByTournamentAndTeam(tournament, team);
            teamNumber = tournamentTeam.getTeamPosition();
            Event event = eventRepository.findEventByNameAndTournament(eventsMarksDto.getEventName(), tournament);
            EventRegister eventRegister = new EventRegister();
            eventRegister.setEvent(event);
            eventRegister.setSwimmer(swimmer);
            eventRegister.setMark(eventsMarksDto.getMark());
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
            eventRegister.setSwimmerNumber(concat);
            // Guardar el registro del evento
            EventRegister savedEventRegister = eventRegisterRepository.save(eventRegister);
            eventsRegisterSaved.add(savedEventRegister);
        }
        return eventsRegisterSaved;
    }



    @Override
    public List<com.sumadeportes.model.entities.EventRegister> getAllEventRegisters() {
        return List.of();
    }

    @Override
    public com.sumadeportes.model.entities.EventRegister getEventRegisterById(Long id) {
        return null;
    }

}
