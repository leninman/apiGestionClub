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

    @Query("SELECT t FROM Tournament t WHERE MONTH(t.startDate) <= :mes AND MONTH(t.endDate) >= :mes")
    List<Tournament> findTournamentsByMonth(@Param("mes") int mes);

    @Query("SELECT t FROM Tournament t WHERE MONTH(t.startDate) <= :mes AND MONTH(t.endDate) >= :mes AND t.endDate < CURRENT_DATE")
    List<Tournament> findFinishedTournamentsByMonth(@Param("mes") int mes);


    @Query("SELECT t FROM Tournament t WHERE t.tournamentName = :name")
    Tournament findTournamentByName(@Param("name") String name);

    Tournament findTournamentByTournamentNameAndStartDateAndEndDate(String tournamentName, LocalDate startDate, LocalDate endDate);

    @Query("SELECT DISTINCT t FROM Tournament t " +
            "JOIN t.events e " +
            "WHERE FUNCTION('MONTH', t.startDate) <= :month " +
            "AND FUNCTION('MONTH', t.endDate) >= :month " +
            "AND EXISTS (" +
            "   SELECT te FROM Test te " +
            "   WHERE te = e.test " +
            "   AND te.gender = :gender " +
            "   AND te.startAge <= :age AND te.endAge >= :age" +
            ")")
    List<Tournament> findTournamentsByMonthGenderAndAge(@Param("month") int month,
                                                                    @Param("gender") String gender,
                                                                    @Param("age") int age);
}
