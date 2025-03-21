package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventDto;
import com.sumadeportes.model.entities.Event;

import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();
    List<Event> saveEvents(List<EventDto> events);
}
