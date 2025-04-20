package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.EventRegister;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRegisterRepository extends CrudRepository<EventRegister, Long> {
    EventRegister findEventRegisterByEventAndSwimmer(Event event, Swimmer swimmer);
    List<EventRegister> findEventRegisterByEvent(Event event);
    boolean existsByEventAndSwimmer(Event event, Swimmer swimmer);


    @Query(value = "SELECT DISTINCT er.* " +
            "FROM event_register er " +
            "INNER JOIN swimmers s ON er.swimmer_document_type = s.document_type AND er.swimmer_document_number = s.document_number " +
            "INNER JOIN events e ON er.event_id = e.id " +
            "INNER JOIN tournaments t ON e.tournament_id = t.id " +
            "WHERE t.tournament_name = :tournamentName " +
            "AND t.start_date = :startDate " +
            "AND t.end_date = :endDate",
            nativeQuery = true)
    List<EventRegister> findEventRegisterByTournamentNameAndDates(String tournamentName, LocalDate startDate, LocalDate endDate);
}
