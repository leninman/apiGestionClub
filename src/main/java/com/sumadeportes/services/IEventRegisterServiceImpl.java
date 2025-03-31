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

        Long swimmerNumber=0L;
        Integer teamNumber= 0;
        Tournament tournament;
        Team team=new Team();
        List<EventRegister> eventsRegisteredSaved=new ArrayList<>();

        // Get the tournament from the event
        Tournament tournamentSaved=tournamentRepository.findTournamentByName(eventRegisterDto.getTournamentName());


       // List<Event> events=eventRegisterDto.getEventsNames().stream().map(eventRepository::findEventByName).toList();
        List<Event> events=eventRegisterDto.getEventsMarks().stream().map(x->eventRepository.findEventByNameAndTournament(x.getEventName(),tournamentSaved)).toList();

        Event event1=events.getFirst();
        tournament=event1.getTournament();

        Optional<Swimmer> swimmer=swimmerService.getSwimmerById(new PersonId(eventRegisterDto.getSwimmerDocumentType(),eventRegisterDto.getSwimmerDocumentNumber()));
        // Check if swimmer is present
        // And get the team of the swimmer
        if(swimmer.isPresent()) {
            team=swimmer.get().getTeam();
        }
        //Get the team number in the TournamentsTeams
        TournamentTeam tournamentTeam=tournamentTeamRepository.findTournamentTeamByTournamentAndTeam(tournament,team);
        teamNumber=tournamentTeam.getTeamPosition();


        for(Event e:events) {
            EventRegister eventRegisterToSave=new EventRegister();
            swimmer.ifPresent(eventRegisterToSave::setSwimmer);
            eventRegisterToSave.setEvent(e);
            List<EventRegister> eventsRegistered=eventRegisterRepository.findEventRegisterByEvent(e);
            if(!eventsRegistered.isEmpty()) {
                List<Integer> swimmerNumbers=new ArrayList<>();
                for(EventRegister eventRegistered:eventsRegistered) {
                    String swimmerNumberString = eventRegistered.getSwimmerNumber();
                    String stringPart = swimmerNumberString.substring(1);
                    Integer partInt = Integer.parseInt(stringPart);
                    swimmerNumbers.add(partInt);
                }
                Long biggestValue= Long.valueOf(Collections.max(swimmerNumbers));
                swimmerNumber = biggestValue + 1;
            }else{
                swimmerNumber=1L;
            }

            String  swimmerNumberFormatted= String.format("%04d", swimmerNumber);
            String concat= teamNumber + swimmerNumberFormatted;
            eventRegisterToSave.setSwimmerNumber(concat);
            //Set the marke to every event
            eventRegisterToSave.setMark(String.valueOf(eventRegisterDto.getEventsMarks().stream()
                    .map(EventsMarks::getMark)
                    .findFirst()
                    .orElse("0")));
            EventRegister eventregisterSaved=eventRegisterRepository.save(eventRegisterToSave);
            eventsRegisteredSaved.add(eventregisterSaved);
        }

        return  eventsRegisteredSaved;
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
