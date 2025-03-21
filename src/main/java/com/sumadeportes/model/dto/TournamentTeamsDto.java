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
public class TournamentTeamsDto {
    private String tournamentName;
    private String startDate;
    private String endDate;
    private List<String> teamsNames;

}
