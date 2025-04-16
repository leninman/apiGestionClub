package com.sumadeportes.services;

import com.sumadeportes.model.dto.SwimmerDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;

import java.util.List;
import java.util.Optional;

public interface ISwimmerService {
    com.sumadeportes.model.entities.Swimmer saveSwimmer(SwimmerDto swimmerDto);
    Swimmer getSwimmerById(PersonId swimmerId);
    List<com.sumadeportes.model.entities.Swimmer> getAllSwimmers();
}
