package com.sumadeportes.model.mapper;

import com.sumadeportes.model.dto.EventDto;

import com.sumadeportes.model.entities.Event;

public class EventMapper {

    public static EventDto toEventDto(Event event) {
        if (event == null) {
            return null;
        }
        return new EventDto(
                event.getTest().getGender(),
                event.getTest().getStartAge(),
                event.getTest().getEndAge(),
                event.getTest().getLength(),
                event.getTest().getStyle(),
                event.getTournament().getTournamentName(),
                event.getEventNumber(),
                event.getName(),
                event.getTournament().getStartDate(),
                event.getTournament().getEndDate()
        );

    }

   /* public static Event toEvent(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        Event event = new Event();
        event.set;

        return event;
    }*/
}
