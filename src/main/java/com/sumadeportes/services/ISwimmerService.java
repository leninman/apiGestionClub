package com.sumadeportes.services;

import com.sumadeportes.model.dto.SwimmerDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;

import java.util.List;


public interface ISwimmerService {
    Swimmer saveSwimmer(SwimmerDto swimmerDto);
    Swimmer getSwimmerById(PersonId swimmerId);
    List<Swimmer> getAllSwimmers();

}
