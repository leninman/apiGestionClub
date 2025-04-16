package com.sumadeportes.model.dto;

import com.sumadeportes.model.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRegisterDto {

    private String swimmerDocumentType;
    private String swimmerDocumentNumber;
    private List<EventsMarksDto> eventsNames = new ArrayList<>();



















    // private String tournamentName;
   // private LocalDate tournamentStartDate;
   // private LocalDate tournamentEndDate;
  //  private List<EventsMarksDto> eventsMarks;

}
