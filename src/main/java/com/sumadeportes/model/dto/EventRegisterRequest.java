package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRegisterRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tournamentName;
    private LocalDate tournamentStartDate;
    private LocalDate tournamentEndDate;
}
