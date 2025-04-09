package com.sumadeportes.services;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Mark;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Test;

import java.util.List;

public interface IMarkService {
    String getMarkByEvent(Swimmer swimmer, Test test);
}
