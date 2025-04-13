package com.sumadeportes.services;

import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.entities.Tournament;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITournamentService {
    List<Tournament> getTournaments();
    List<Tournament> getTournamentsByDate(LocalDate date);
    Tournament getTournamentByName(String name);
    List<Tournament> gestTournamentsByMonth(int month);
    List<Tournament> getFinishedTournamentsByMonth(int month);
    Tournament createTournament(String tournamentName,LocalDate startDate,LocalDate endDate,List<String> teamsNames,Integer teamNumber);
    List<Tournament> getTournamentsByMonthGenderAndAge(Integer month,String gender,Integer age);
}
