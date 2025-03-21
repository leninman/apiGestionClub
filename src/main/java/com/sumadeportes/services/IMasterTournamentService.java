package com.sumadeportes.services;

import com.sumadeportes.model.entities.MasterTournament;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IMasterTournamentService {
    List<MasterTournament> getAllMasterTournaments();
}
