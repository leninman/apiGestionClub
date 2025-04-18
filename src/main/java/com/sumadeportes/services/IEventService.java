package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;

import java.time.LocalDate;
import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();

    List<Event> saveEvents(EventDto events);

    List<Event> getEventsByGenderAgeTournament(String gender, Integer age, Long tournamentId);


    List<Event> getOutOfCategoryEvents(String gender, int age, Long tournamentId);

    List<Event> getAllEndedEvents(String gender, Integer age, Long tournamentId);


}

