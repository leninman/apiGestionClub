package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    private Long id;
    private String gender;
     private Integer startAge;
     private Integer endAge;
     private Integer length;
     private String style;
    private String tournamentName;
     private Integer eventNumber;
    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
}
