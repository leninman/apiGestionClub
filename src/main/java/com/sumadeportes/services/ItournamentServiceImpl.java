package com.sumadeportes.services;

import com.sumadeportes.model.entities.Team;
import com.sumadeportes.model.entities.Tournament;
import com.sumadeportes.model.entities.TournamentTeam;
import com.sumadeportes.model.repositories.TeamRepository;
import com.sumadeportes.model.repositories.TournamentRepository;
import com.sumadeportes.model.repositories.TournamentTeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ItournamentServiceImpl implements ITournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentTeamRepository tournamentTeamRepository;
    private final TeamRepository teamRepository;


    public ItournamentServiceImpl(TournamentRepository tournamentRepository, TournamentTeamRepository tournamentTeamRepository, TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }


    @Override
    public List<Tournament> gestTournamentsByMonth(int month) {
        return tournamentRepository.findTournamentsByMonth(month);
    }

    @Override
    public List<Tournament> getFinishedTournamentsByMonth(int month) {
        return tournamentRepository.findFinishedTournamentsByMonth(month);
    }

    @Override
    @Transactional
    public Tournament createTournament(String tournament, LocalDate startDate,LocalDate endDate,List<String> teamNames,Integer teamNumber) {
        Tournament savedTournament = tournamentRepository.findTournamentByTournamentNameAndStartDateAndEndDate(tournament,startDate,endDate);
        Tournament t=null;
        if (savedTournament == null) {


            t = new Tournament(tournament, startDate, endDate);
            Tournament newTournament = tournamentRepository.save(t);
            List<TournamentTeam> tournamentTeams = new ArrayList<>();
            int position = 0;

            for (String teamName : teamNames) {
                Team team = teamRepository.findTeamByteamName(teamName);
                TournamentTeam tournamentTeam = new TournamentTeam();
                tournamentTeam.setTeam(team);
                tournamentTeam.setTournament(newTournament);
                //tournamentTeam.setTeamPosition(++position);
                tournamentTeam.setTeamPosition(teamNumber);
                tournamentTeams.add(tournamentTeam);
            }

            tournamentTeamRepository.saveAll(tournamentTeams);
        }
        return t;
    }

    @Override
    public List<Tournament> getTournamentsByMonthGenderAndAge(Integer month, String gender, Integer age) {
        return tournamentRepository.findTournamentsByMonthGenderAndAge(month,gender,age);
    }
}
