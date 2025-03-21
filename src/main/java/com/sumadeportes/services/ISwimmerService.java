package com.sumadeportes.services;

import com.sumadeportes.model.dto.SwimmerDto;
import com.sumadeportes.model.dto.UserDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ISwimmerService {
    Swimmer saveSwimmer(SwimmerDto swimmerDto);
    Optional<Swimmer> getSwimmerById(PersonId swimmerId);
    List<Swimmer> getAllSwimmers();
}
