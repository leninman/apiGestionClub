package com.sumadeportes.services;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Mark;
import com.sumadeportes.model.entities.Swimmer;

import java.util.List;

public interface IMarkService {
    List<Float> getAllMarksBySimmerAndEvent(Swimmer swimmer, Event event);
}
