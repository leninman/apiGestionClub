package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.EventRegister;
import com.sumadeportes.model.entities.Swimmer;

import java.util.List;

public interface IEventsRegisterService {
    List<EventRegister> saveEventRegister(EventRegisterDto eventRegister);
    List<EventRegister> getAllEventRegisters();
    EventRegister getEventRegisterById(Long id);
    void deleteEventRegister(Long id);

}
