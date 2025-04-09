package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -2532880776646100450L;
    private Long id;
    private String tournamentName;
    private LocalDate startDate;
    private LocalDate endDate;
}
