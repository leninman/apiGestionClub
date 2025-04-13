package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.RespDto;
import com.sumadeportes.services.IMasterTournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/master-tournaments")
@CrossOrigin(origins = "https://erika-github.github.io")
public class MasterTournamentController {
    private final IMasterTournamentService masterTournamentService;

    public MasterTournamentController(IMasterTournamentService masterTournamentService) {
        this.masterTournamentService = masterTournamentService;
    }

    @PostMapping("/getAll")
    public ResponseEntity<RespDto> findAllMasterTournaments() {
        RespDto response = new RespDto();
        response.setMessage("Tournaments found");
        response.setCode("200");
        response.setData(masterTournamentService.getAllMasterTournaments());
        return ResponseEntity.ok(response);
    }
}
