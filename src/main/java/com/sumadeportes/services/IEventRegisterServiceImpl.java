package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.entities.*;
import com.sumadeportes.model.repositories.EventRegisterRepository;
import com.sumadeportes.model.repositories.EventRepository;
import com.sumadeportes.model.repositories.MarkRepository;
import com.sumadeportes.model.repositories.TournamentTeamRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IEventRegisterServiceImpl implements IEventsRegisterService{

    private final EventRepository eventRepository;
    private final ISwimmerService swimmerService;
    private final EventRegisterRepository eventRegisterRepository;
    private final TournamentTeamRepository tournamentTeamRepository;
    private final MarkRepository markRepository;

    public IEventRegisterServiceImpl(EventRepository eventRepository, ISwimmerService swimmerService, EventRegisterRepository eventRegisterRepository, TournamentTeamRepository tournamentTeamRepository, MarkRepository markRepository) {
        this.eventRepository = eventRepository;
        this.swimmerService = swimmerService;
        this.eventRegisterRepository = eventRegisterRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.markRepository = markRepository;
    }

    @Override
    public List<EventRegister> saveEventRegister(EventRegisterDto eventRegisterDto) {

        Long swimmerNumber=0L;
        Integer teamNumber= 0;
        Tournament tournament;
        Team team=new Team();
        List<EventRegister> eventsRegisteredSaved=new ArrayList<>();

        List<Event> events=eventRegisterDto.getEventsNames().stream().map(eventRepository::findEventByName).toList();
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
            if(swimmer.isPresent()) {
                Float mark=getMark(swimmer.get(),e);
                eventRegisterToSave.setMark(mark);
            }
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

    public Float getMark(Swimmer swimmer, Event event) {

        return markRepository.findMarkByEventAndSwimmer(event, swimmer)
                .stream()
                .map(Mark::getMark)
                .min(Float::compareTo)
                .orElse(0F);


    }
}
