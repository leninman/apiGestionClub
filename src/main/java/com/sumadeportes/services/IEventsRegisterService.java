package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Swimmer;

import java.util.List;

public interface IEventsRegisterService {
    List<com.sumadeportes.model.entities.EventRegister> saveEventRegister(EventRegisterDto eventRegister);
    boolean isSwimmerRegistered(Event event, Swimmer swimmer);
    List<com.sumadeportes.model.entities.EventRegister> getAllEventRegisters();
    com.sumadeportes.model.entities.EventRegister getEventRegisterById(Long id);
    void deleteEventRegister(Long id);

}
