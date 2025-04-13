package com.sumadeportes.services;

import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ITeamsServiceImpl implements ITeamService {
    private final TeamRepository teamRepository;

    public ITeamsServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public List<Team> getAllTeams() {
        //return (List<Team>) teamRepository.findAll();
        return teamRepository.findTeamsByTeamName("Barracudas SUMA");
    }
   }
