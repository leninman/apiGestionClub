package com.sumadeportes.services;

import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Team;

import java.util.List;

public interface ITeamService {
    List<Team> getAllTeams();
}
