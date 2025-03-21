package com.sumadeportes.model.mapper;

import com.sumadeportes.model.dto.SwimmerDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.model.entities.Team;

public class SwimmerMapper {
    public static SwimmerDto toSwimmerDto(Swimmer swimmer) {
        if (swimmer == null) {
            return null;
        }
        return new SwimmerDto(
                swimmer.getSwimmerId().getDocumentType(),
                swimmer.getSwimmerId().getDocumentNumber(),
                swimmer.getFirstName(),
                swimmer.getSecondName(),
                swimmer.getFirstSurename(),
                swimmer.getSecondSurename(),
                swimmer.getAge(),
                swimmer.getGender(),
                swimmer.getBirthDate(),
                swimmer.getIncomingDate(),
                swimmer.getEmail(),
                swimmer.getSwimmerKey(),
                swimmer.getStatus(),
                swimmer.getYearsActive(),
                swimmer.getMonthsActive(),
                swimmer.getDaysActive(),
                swimmer.getRepresentorName(),
                swimmer.getRepresentorSurename(),
                swimmer.getPhone(),
                swimmer.getPlain(),
                swimmer.getLevel(),
                swimmer.getMonday(),
                swimmer.getTuesday(),
                swimmer.getWednesday(),
                swimmer.getThursday(),
                swimmer.getFriday(),
                swimmer.getSaturday(),
                swimmer.getTeam().getTeamName()
                );

    }

    public static Swimmer toSwimmer(SwimmerDto swimmerDto) {
        if (swimmerDto == null) {
            return null;
        }
       Swimmer swimmer = new Swimmer();
         swimmer.setSwimmerId(new PersonId(swimmerDto.getDocumentType(), swimmerDto.getDocumentNumber()));
            swimmer.setFirstName(swimmerDto.getFirstName());
            swimmer.setSecondName(swimmerDto.getSecondName());
            swimmer.setFirstSurename(swimmerDto.getFirstSurename());
            swimmer.setSecondSurename(swimmerDto.getSecondSurename());
            swimmer.setAge(swimmerDto.getAge());
            swimmer.setGender(swimmerDto.getGender());
            swimmer.setBirthDate(swimmerDto.getBirthDate());
            swimmer.setIncomingDate(swimmerDto.getIncomingDate());
            swimmer.setEmail(swimmerDto.getEmail());
            swimmer.setSwimmerKey(swimmerDto.getSwimmerKey());
            swimmer.setStatus(swimmerDto.getStatus());
            swimmer.setYearsActive(swimmerDto.getYearsActive());
            swimmer.setMonthsActive(swimmerDto.getMonthsActive());
            swimmer.setDaysActive(swimmerDto.getDaysActive());
            swimmer.setRepresentorName(swimmerDto.getRepresentorName());
            swimmer.setRepresentorSurename(swimmerDto.getRepresentorSurename());
            swimmer.setPhone(swimmerDto.getPhone());
            swimmer.setPlain(swimmerDto.getPlain());
            swimmer.setLevel(swimmerDto.getLevel());
            swimmer.setMonday(swimmerDto.getMonday());
            swimmer.setTuesday(swimmerDto.getTuesday());
            swimmer.setWednesday(swimmerDto.getWednesday());
            swimmer.setThursday(swimmerDto.getThursday());
            swimmer.setFriday(swimmerDto.getFriday());
            swimmer.setSaturday(swimmerDto.getSaturday());
        return swimmer;
    }
}
