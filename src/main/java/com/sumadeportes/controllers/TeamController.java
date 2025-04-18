package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.respDto;
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
    public ResponseEntity<respDto> getAllTeams() {
        respDto response = new respDto();
        response.setMessage("Teams found");
        response.setCode("200");
        response.setData(teamService.getAllTeams());
        return ResponseEntity.ok(response);
    }
}
