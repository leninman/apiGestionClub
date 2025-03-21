package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.MasterTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTournamentRepository extends JpaRepository<MasterTournament, Long> {
}
