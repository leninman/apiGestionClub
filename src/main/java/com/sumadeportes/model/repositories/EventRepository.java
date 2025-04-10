package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findEventByTournament(Tournament tournament);
    Event findEventByNameAndTournament(String name, Tournament tournament);

    @Query(value="SELECT e.* FROM events e " +
            "INNER JOIN tournaments t ON e.tournament_id = t.id " +
            "INNER JOIN tests te ON e.test_id = te.id " +
            "WHERE te.gender = :gender " +
            "AND :age BETWEEN te.start_age AND te.end_age " +
            "AND t.tournament_name = :tournamentName", nativeQuery = true)
    List<Event> findAllWithTournamentAndTeams(String gender,Integer age,String tournamentName);

    /*All the ended-events in the month and in category*/
    @Query(value="SELECT e.* FROM events e " +
            "INNER JOIN tournaments t ON e.tournament_id = t.id " +
            "INNER JOIN tests te ON e.test_id = te.id " +
            "WHERE te.gender = :gender " +
            "AND :age BETWEEN te.start_age AND te.end_age " +
            "AND t.tournament_name = :tournamentName " +
            "AND :dateToday > t.end_date " +
            "AND :month BETWEEN MONTH(t.start_date) AND MONTH(t.end_date)", nativeQuery = true)
    List<Event> findAllWithTournamentAndTeamsEnded(String gender, Integer age, String tournamentName, LocalDate dateToday, int month);


    /*All the events out of swimmer category*/
    @Query(value="SELECT e.* FROM events e " +
                        "INNER JOIN tournaments t ON e.tournament_id = t.id " +
                        "WHERE e.id NOT IN (" +
                        "SELECT e.id FROM events e " +
                        "INNER JOIN tournaments t ON e.tournament_id = t.id " +
                       "INNER JOIN tests te ON e.test_id = te.id " +
                        "WHERE te.gender = :gender " +
                        "AND :age BETWEEN te.start_age AND te.end_age) " +
                       "AND :month BETWEEN MONTH(t.start_date) AND MONTH(t.end_date)", nativeQuery = true)
    List<Event> findAllEventsNotInCategory(String gender,int age, int month);




}
