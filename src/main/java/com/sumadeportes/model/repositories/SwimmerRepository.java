package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SwimmerRepository extends CrudRepository<Swimmer, PersonId> {

    Swimmer findSwimmerBySwimmerId(PersonId swimmerId);

    @Query(value="SELECT s.document_number FROM swimmers s " +
            "WHERE s.document_number LIKE 'documentNumber%'",nativeQuery = true)
    List<String> findDocumentNumberBySwimmerId(String  documentNumber);
}
