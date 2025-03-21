package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findEventByTournament(Tournament tournament);
}
