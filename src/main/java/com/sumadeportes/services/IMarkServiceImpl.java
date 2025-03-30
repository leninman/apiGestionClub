package com.sumadeportes.services;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Mark;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.repositories.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IMarkServiceImpl implements IMarkService {

    private final MarkRepository markRepository;


    public IMarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public List<Float> getAllMarksBySimmerAndEvent(Swimmer swimmer, Event event) {
        return markRepository.findMarkByEventAndSwimmer(event, swimmer)
                .stream()
                .map(Mark::getMark)
                .toList();
    }
}
