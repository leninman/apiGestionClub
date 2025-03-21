package com.sumadeportes.services;

import com.sumadeportes.model.entities.MasterTournament;
import com.sumadeportes.model.repositories.MasterTournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IMasterTournamentServiceImpl implements IMasterTournamentService{
    private final MasterTournamentRepository masterTournamentRepository;

    public IMasterTournamentServiceImpl(MasterTournamentRepository masterTournamentRepository) {
        this.masterTournamentRepository = masterTournamentRepository;
    }

    @Override
    public List<MasterTournament> getAllMasterTournaments() {
        return masterTournamentRepository.findAll();
    }
}
