package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.entities.TournamentTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long> {
    List<TournamentTeam> findTournamentTeamsByTournament(Tournament tournament);

    @Query("SELECT tt FROM TournamentTeam tt JOIN FETCH tt.tournament JOIN FETCH tt.team")
    List<TournamentTeam> findAll();

    TournamentTeam findTournamentTeamByTournamentAndTeam(Tournament tournament, Team team);
}
