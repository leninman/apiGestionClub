package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findTeamByteamName(String teamName);
    List<Team> findTeamsByTeamName(String teamName);
}
