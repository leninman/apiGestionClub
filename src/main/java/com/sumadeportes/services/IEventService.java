package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;

import java.time.LocalDate;
import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();

    void saveEvents(EventDto events);

    List<Event> getEventsByTournament(Tournament tournament);

    List<Event> getEventsByGenderAgeTournament(String gender, Integer age, Long tournamentId);
    Event getEventById(Long id);

    List<Event> getOutOfCategoryEvents(String gender, int age, Long tournamentId);

    List<Event> getAllEndedEvents(String gender, Integer age, Long tournamentId);

    Boolean isEventRegistered(Event event, String swimmerDocumentType, String swimmerDocumentNumber);
}

