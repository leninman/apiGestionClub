package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.*;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.entities.TournamentTeam;
import com.sumadeportes.services.ITournamentService;
import com.sumadeportes.services.ITournamentTeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/tournament")
@CrossOrigin(origins = "https://erika-github.github.io")
public class TournamentController {

    private final ITournamentService tournamentService;
    private final ITournamentTeamService tournamentTeamService;

    public TournamentController(ITournamentService tournamentService, ITournamentTeamService tournamentTeamService) {
        this.tournamentService = tournamentService;
        this.tournamentTeamService = tournamentTeamService;
    }

    @PostMapping("/getList")
    public ResponseEntity<RespDto> getAllTournaments() {

        RespDto respDto = new RespDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<Tournament> tournaments = tournamentService.getTournaments();
        if (tournaments.isEmpty()) {
            respDto.setCode("404");
            respDto.setMessage("No tournaments found");
            respDto.setData(null);
            return new ResponseEntity<>(respDto, HttpStatus.NOT_FOUND);
        }
        List<TournamentResponse> tournamentResponses = tournaments.stream().map(tournament -> {
            String startDateFormatted = tournament.getStartDate().format(formatter);
            String endDateFormatted = tournament.getEndDate().format(formatter);
            String tournamentNameWithDates = tournament.getTournamentName() + " desde " + startDateFormatted + " hasta " + endDateFormatted;
            return new TournamentResponse(tournament.getId(), tournamentNameWithDates, tournament.getStartDate(), tournament.getEndDate());
        }).toList();
        respDto.setCode("200");
        respDto.setData(tournamentResponses);
        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/getTournamentsTeamsList")
    public ResponseEntity<RespDto> getAllTournamentsTeams() {

        RespDto respDto = new RespDto();

        List<TournamentTeam> tournamentsTeams = tournamentTeamService.findAllTournamentsTeams();
        if (tournamentsTeams.isEmpty()) {
            respDto.setCode("404");
            respDto.setMessage("No tournamentsTeams found");
            respDto.setData(null);
            return new ResponseEntity<>(respDto, HttpStatus.NOT_FOUND);
        }

        respDto.setCode("200");
        respDto.setData(tournamentsTeams);
        return ResponseEntity.ok(respDto);

    }
    @PostMapping("/getProgrammed")
    public ResponseEntity<RespDto> getProgrammedTournaments(@RequestBody TournamentRequest tournamentRequest) {
        RespDto respDto = new RespDto();
        List<Tournament> tournaments = tournamentService.getTournamentsByMonthGenderAndAge(tournamentRequest.getMonth(), tournamentRequest.getGender(), tournamentRequest.getAge());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (tournaments.isEmpty()) {
            respDto.setCode("404");
            respDto.setMessage("No tournaments found");
            respDto.setData(null);
            return new ResponseEntity<>(respDto, HttpStatus.NOT_FOUND);
        }
        List<TournamentsResponse> tournamentsResponses = tournaments.stream().map(tournament -> {
            return new TournamentsResponse(
                    tournament.getStartDate().getDayOfMonth(),
                    tournament.getEvents().stream()
                            .map(Event::getName)
                            .toList(), // Ajusta según la estructura de datos
                    tournaments.stream().map(t->t.getTournamentName())
                            .toList(), // Ajusta según la estructura de datos
                    tournament.getEndDate().getDayOfMonth()
            );
        }).toList();
        respDto.setCode("200");
        respDto.setData(tournamentsResponses);
        return ResponseEntity.ok(respDto);
    }


    @PostMapping("/create")
    public ResponseEntity<RespDto> createTournament(@RequestBody TournamentTeamsDto tournamentTeamsDto) {
        RespDto response = new RespDto();
        try {
            Tournament tournament = tournamentService.createTournament(tournamentTeamsDto.getTournamentName(), tournamentTeamsDto.getStartDate(), tournamentTeamsDto.getEndDate(), tournamentTeamsDto.getTeamsNames(), tournamentTeamsDto.getTeamNumber());
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
