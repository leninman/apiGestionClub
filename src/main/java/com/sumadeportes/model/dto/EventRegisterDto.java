package com.sumadeportes.model.dto;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Tournament;

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
    private List<String> eventsNames;

}
