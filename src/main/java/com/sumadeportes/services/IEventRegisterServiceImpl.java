package com.sumadeportes.services;



import com.sumadeportes.model.dto.*;
import com.sumadeportes.model.entities.*;
import com.sumadeportes.model.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        long swimmerNumber = 0;
        Integer teamNumber;
        boolean swimmerNumberExists = false;
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
//            List<EventRegister> eventsRegistered = eventRegisterRepository.findEventRegisterByEvent(event);
            List<EventRegister> eventsRegistered = eventRegisterRepository.findEventsRegisteredByTournament(tournament.getTournamentName(), tournament.getStartDate(), tournament.getEndDate());

            if (!eventsRegistered.isEmpty()) {
                List<Integer> swimmerNumbers = new ArrayList<>();
                for (EventRegister eventRegistered : eventsRegistered) {
                    String swimmerNumberString = eventRegistered.getSwimmerNumber();
                    String stringPart = swimmerNumberString.substring(1);
                    Integer partInt = Integer.parseInt(stringPart);
                    swimmerNumbers.add(partInt);
                }
                Long biggestValue = Long.valueOf(Collections.max(swimmerNumbers));
                if(!swimmerNumberExists) {
                    swimmerNumber = biggestValue + 1;
                    swimmerNumberExists = true;
                }
            } else {
                if(!swimmerNumberExists) {
                    swimmerNumber = 1L;
                    swimmerNumberExists = true;
                }
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
    public List<EventRegister> getAllEventRegisters() {
        return List.of();
    }

    @Override
    public EventRegister getEventRegisterById(Long id) {
        return null;
    }

    @Override
    public RegisterSheet findEventsRegistersByTournaments(EventRegisterRequest eventRegisterRequest) {
        Map<Swimmer, List<EventRegister>> groupedBySwimmer = new HashMap<>();
        List<EventRegisterResponse> eventRegisterResponses = new ArrayList<>();
        RegisterSheet registerSheet = new RegisterSheet();
        registerSheet.setTournament(eventRegisterRequest.getTournamentName());
        List<EventRegister> eventRegisters = eventRegisterRepository.findEventRegisterByTournamentNameAndDates(
                eventRegisterRequest.getTournamentName(),
                eventRegisterRequest.getTournamentStartDate(),
                eventRegisterRequest.getTournamentEndDate()
        );

        // Agrupar por nadador
        for (EventRegister eventRegister : eventRegisters) {
            groupedBySwimmer.computeIfAbsent(eventRegister.getSwimmer(), k -> new ArrayList<>()).add(eventRegister);
        }

        // Mapear los datos agrupados
        for (Map.Entry<Swimmer, List<EventRegister>> entry : groupedBySwimmer.entrySet()) {
            Swimmer swimmer = entry.getKey();
            List<EventRegister> swimmerEventRegisters = entry.getValue();

            EventRegisterResponse response = new EventRegisterResponse();
            response.setSwimmerDocType(swimmer.getSwimmerId().getDocumentType());
            response.setSwimmerDocNumber(swimmer.getSwimmerId().getDocumentNumber());
            response.setSwimmerName(swimmer.getFirstName());
            response.setSwimmerLastName(swimmer.getFirstSurename());
            response.setSwimmerGender(swimmer.getGender());
            response.setSwimmerAge(String.valueOf(swimmer.getAge()));
            response.setSwimmerNumber(swimmerEventRegisters.getFirst().getSwimmerNumber());
            registerSheet.setTeamName(swimmer.getTeam().getTeamName());



            // Mapear los eventos y marcas
            List<EventMarkDto> eventsMarksDtos = new ArrayList<>();
            for (EventRegister swimmerEventRegister : swimmerEventRegisters) {
                EventMarkDto eventsMarksDto = new EventMarkDto();
                eventsMarksDto.setEventName(swimmerEventRegister.getEvent().getName());
                eventsMarksDto.setMark(swimmerEventRegister.getMark());
                eventsMarksDtos.add(eventsMarksDto);
            }
            response.setEvents(eventsMarksDtos);

            eventRegisterResponses.add(response);
        }
        registerSheet.setEventRegisterResponses(eventRegisterResponses);

        return registerSheet;
    }



}
