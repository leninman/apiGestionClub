package com.sumadeportes.services;

import com.sumadeportes.model.entities.Event;
import com.sumadeportes.model.entities.Mark;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Test;
import com.sumadeportes.model.repositories.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.min;

@Service
public class IMarkServiceImpl implements IMarkService {

    private final MarkRepository markRepository;


    public IMarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public String getMarkByEvent(Swimmer swimmer, Test test) {
        List<Mark> marks = markRepository.findMarkByTestAndSwimmer(test, swimmer);
        if (marks.isEmpty()) {
            return "00:00,00";
        }
        Optional<Float> minMark = marks.stream()
                .map(mark -> convertToSeconds(mark.getMark()))
                .min(Float::compareTo);

        return minMark.map(this::convertToTimeFormat).orElse("00:00,00");



    }
    private Float convertToSeconds(String mark) {
        String[] parts = mark.split("[:,]");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        int milliseconds = Integer.parseInt(parts[2]);
        return minutes * 60 + seconds + milliseconds / 100.0f;
    }
    private String convertToTimeFormat(Float seconds) {
        int minutes = (int) (seconds / 60);
        float remainingSeconds = seconds % 60;
        int wholeSeconds = (int) remainingSeconds;
        int milliseconds = Math.round((remainingSeconds - wholeSeconds) * 100);
        return String.format("%02d:%02d,%02d", minutes, wholeSeconds, milliseconds);
    }

}
