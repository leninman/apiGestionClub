package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SwimmerRepository extends CrudRepository<Swimmer, PersonId> {

    Optional<Swimmer> findSwimmerBySwimmerId(PersonId swimmerId);

}
