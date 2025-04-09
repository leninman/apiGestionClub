package com.sumadeportes.services;

import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.entities.TournamentTeam;
import com.sumadeportes.model.repositories.TournamentTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItournamentTeamServiceImpl implements ITournamentTeamService{

    private final TournamentTeamRepository tournamentTeamRepository;

    public ItournamentTeamServiceImpl(TournamentTeamRepository tournamentTeamRepository) {
        this.tournamentTeamRepository = tournamentTeamRepository;
    }

    @Override
    public List<TournamentTeam> findTournamentTeamsByTournament(Tournament tournament) {
        return tournamentTeamRepository.findTournamentTeamsByTournament(tournament);
    }

    @Override
    public List<TournamentTeam> findAllTournamentsTeams() {
        return tournamentTeamRepository.findAll();
    }

    @Override
    public TournamentTeam findTeamByTournamentAndTeam(Tournament tournament, Team team) {
        return tournamentTeamRepository.findTournamentTeamByTournamentAndTeam(tournament, team);
    }
}
