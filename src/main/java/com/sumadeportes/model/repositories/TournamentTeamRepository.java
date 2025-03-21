package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.TournamentTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long> {
}
