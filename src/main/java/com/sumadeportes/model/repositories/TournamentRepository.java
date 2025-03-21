package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query("SELECT t FROM Tournament t WHERE :fecha BETWEEN t.startDate AND t.endDate")
    List<Tournament> findTournamentsByDate(@Param("fecha") LocalDate fecha);

    @Query("SELECT t FROM Tournament t WHERE t.tournamentName = :name")
    Tournament findTournamentByName(@Param("name") String name);

    Tournament findTournamentByTournamentNameAndStartDateAndEndDate(String tournamentName, String startDate, String endDate);


}
