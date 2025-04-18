package com.sumadeportes.services;

import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.entities.TournamentTeam;

import java.util.List;

public interface ITournamentTeamService {
     List<TournamentTeam> findAllTournamentsTeams();
}
