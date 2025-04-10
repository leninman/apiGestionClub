package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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

    /*All the events out of swimmer category*/
    @Query(value="SELECT e.* FROM events e " +
                        "WHERE e.id NOT IN (" +
                        "SELECT e.id FROM events e " +
                        "INNER JOIN tests te ON e.test_id = te.id " +
                        "WHERE te.gender = :gender "  +
                        "AND :age BETWEEN te.start_age AND te.end_age)", nativeQuery = true)
    List<Event> findAllEventsNotInCategory(String gender,int age);




}
