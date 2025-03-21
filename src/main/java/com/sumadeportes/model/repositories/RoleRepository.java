package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Long> {
     RoleEntity findRoleByName(String roleName);
}
