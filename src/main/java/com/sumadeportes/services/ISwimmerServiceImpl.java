package com.sumadeportes.services;

import com.sumadeportes.model.dto.SwimmerDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.mapper.SwimmerMapper;
import com.sumadeportes.model.repositories.SwimmerRepository;
import com.sumadeportes.model.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ISwimmerServiceImpl implements ISwimmerService {

    private final SwimmerRepository swimmerRepository;
    private final TeamRepository teamRepository;

    public ISwimmerServiceImpl(SwimmerRepository swimmerRepository, TeamRepository teamRepository) {
        this.swimmerRepository = swimmerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public com.sumadeportes.model.entities.Swimmer saveSwimmer(SwimmerDto swimmerDto) {
        com.sumadeportes.model.entities.Swimmer swimmer = SwimmerMapper.toSwimmer(swimmerDto);
        Team team=teamRepository.findTeamByteamName(swimmerDto.getTeam());
        if(team!=null){
            swimmer.setTeam(team);
        }else{
            swimmer.setTeam(null);
        }

        return swimmerRepository.save(swimmer);
    }

    @Override
    public Swimmer getSwimmerById(PersonId swimmerId) {
        return swimmerRepository.findSwimmerBySwimmerId(swimmerId);
    }

    @Override
    public List<com.sumadeportes.model.entities.Swimmer> getAllSwimmers() {
        return (List<com.sumadeportes.model.entities.Swimmer>) swimmerRepository.findAll();
    }
}
