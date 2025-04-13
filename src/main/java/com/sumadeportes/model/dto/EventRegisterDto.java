package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRegisterDto {

    private String swimmerDocumentType;
    private String swimmerDocumentNumber;
    private String tournamentName;
    private List<EventsMarksDto> eventsMarks;

}
