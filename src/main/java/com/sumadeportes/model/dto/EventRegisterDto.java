package com.sumadeportes.model.dto;

import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Tournament;
import jdk.jfr.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRegisterDto {

    private Long tournamentId;
    private Long eventId;
    private String swimmerDocumentType;
    private String swimmerDocumentNumber;
}
