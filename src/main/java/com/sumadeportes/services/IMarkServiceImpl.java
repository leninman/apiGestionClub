package com.sumadeportes.services;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Mark;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.model.repositories.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.min;

@Service
public class IMarkServiceImpl implements IMarkService {

    private final MarkRepository markRepository;


    public IMarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public Float getMarkByEvent(Swimmer swimmer, Test test) {
        List<Mark> marks = markRepository.findMarkByTestAndSwimmer(test, swimmer);
        if (marks.isEmpty()) {
            return 0F;
        }
        Float minMark = 0F;
        List<String> marksList = new ArrayList<>();
        for (Mark mark : marks) {
            marksList.add(mark.getMark());
        }
        List<Float> marksListFloat = new ArrayList<>();
        for (String mark : marksList) {
            marksListFloat.add(Float.parseFloat(mark));
        }

        return min(marksListFloat);


    }
}
