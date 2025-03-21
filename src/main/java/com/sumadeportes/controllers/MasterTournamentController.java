package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.services.IMasterTournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master-tournaments")
public class MasterTournamentController {
    private final IMasterTournamentService masterTournamentService;

    public MasterTournamentController(IMasterTournamentService masterTournamentService) {
        this.masterTournamentService = masterTournamentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<respDto> findAllMasterTournaments() {
        respDto response = new respDto();
        response.setMessage("Tournaments found");
        response.setCode("200");
        response.setData(masterTournamentService.getAllMasterTournaments());
        return ResponseEntity.ok(response);
    }
}
