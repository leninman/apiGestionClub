package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.EventRegister;
import com.sumadeportes.model.entities.Swimmer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRegisterRepository extends CrudRepository<EventRegister, Long> {
    EventRegister findEventRegisterByEventAndSwimmer(Event event, Swimmer swimmer);
    List<EventRegister> findEventRegisterByEvent(Event event);

}
