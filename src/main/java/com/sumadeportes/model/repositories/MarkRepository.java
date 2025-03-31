package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Mark;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarkRepository extends CrudRepository<Mark, Long> {
   List<Mark> findMarkByTestAndSwimmer(Test test, Swimmer swimmer);
}
