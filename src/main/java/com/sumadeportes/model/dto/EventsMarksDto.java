package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventsMarksDto {
    private String tournamentName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String eventName;
    private String mark;
}
