package com.sumadeportes.services;

import com.sumadeportes.model.dto.EventRegisterDto;
import com.sumadeportes.model.dto.EventRegisterRequest;
import com.sumadeportes.model.dto.EventRegisterResponse;
import com.sumadeportes.model.dto.RegisterSheet;
import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.EventRegister;
import com.sumadeportes.model.entities.Swimmer;

import java.time.LocalDate;
import java.util.List;

public interface IEventsRegisterService {
    List<com.sumadeportes.model.entities.EventRegister> saveEventRegister(EventRegisterDto eventRegister);
    List<com.sumadeportes.model.entities.EventRegister> getAllEventRegisters();
    EventRegister getEventRegisterById(Long id);
    RegisterSheet findEventsRegistersByTournaments(EventRegisterRequest eventRegisterRequest);

}
