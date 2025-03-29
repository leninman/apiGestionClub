package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;

import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();

    void saveEvents(EventDto events);

    List<Event> getEventsByTournament(Tournament tournament);

    List<Event> getEventsByGenderAgeTournament(String gender, Integer age, String tournament);
}

