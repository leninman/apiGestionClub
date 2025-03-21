package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.PersonId;

import com.sumadeportes.model.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, PersonId> {
    Optional<UserEntity> findByEmail(String email);
}
