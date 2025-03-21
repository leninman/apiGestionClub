package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.TournamentTeamsDto;
import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.services.ITournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tournament")
@CrossOrigin(origins = "https://erika-github.github.io")
public class TournamentController {

    private final ITournamentService tournamentService;

    public TournamentController(ITournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/getList")
    public ResponseEntity<respDto> getAllEvents() {

        respDto respDto = new respDto();

        List<Tournament> tournaments = tournamentService.getTournaments();
        respDto.setData(tournaments);
        return ResponseEntity.ok(respDto);

    }

    @GetMapping("/getListByDate")
    public ResponseEntity<respDto> getAllEventsByDate(@RequestParam LocalDate date) {

        respDto respDto = new respDto();

        List<Tournament> tournaments = tournamentService.getTournamentsByDate(date);
        respDto.setData(tournaments);
        return ResponseEntity.ok(respDto);

    }
    @PostMapping("/create")
    public ResponseEntity<respDto> createTournament(@RequestBody TournamentTeamsDto tournamentTeamsDto) {
        respDto response = new respDto();
        try {
            Tournament tournament = tournamentService.createTournament(tournamentTeamsDto.getTournamentName(), tournamentTeamsDto.getStartDate(), tournamentTeamsDto.getEndDate(), tournamentTeamsDto.getTeamsNames());
            if(tournament==null) {
                response.setMessage("Tournament already exists");
                response.setCode("409");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);

            }
            response.setMessage("Tournament saved successfully");
            response.setCode("201");
            response.setData(tournament);
            return new ResponseEntity<>(response, HttpStatus.CREATED);




        } catch (Exception e) {
            response.setMessage("Error saving tournament");
            response.setCode("500");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
