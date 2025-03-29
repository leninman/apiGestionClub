package com.sumadeportes.services;

import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.entities.TournamentTeam;

import java.util.List;

public interface ITournamentTeamService {
     List<TournamentTeam> findTournamentTeamsByTournament(Tournament tournament);
     List<TournamentTeam> findAllTournamentsTeams();
     TournamentTeam findTeamByTournamentAndTeam(Tournament tournament, Team team);
}
