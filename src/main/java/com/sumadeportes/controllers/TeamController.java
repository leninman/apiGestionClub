package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.RespDto;
import com.sumadeportes.services.ITeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "https://erika-github.github.io")
public class TeamController {

    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }
    @PostMapping("/getAll")
    public ResponseEntity<RespDto> getAllTeams() {
        RespDto response = new RespDto();
        response.setMessage("Teams found");
        response.setCode("200");
        response.setData(teamService.getAllTeams());
        return ResponseEntity.ok(response);
    }
}
