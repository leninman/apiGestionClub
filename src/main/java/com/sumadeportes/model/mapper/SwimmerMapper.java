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
                swimmer.getRepresentorName(),
                swimmer.getRepresentorSurename(),
                swimmer.getPhone(),
                swimmer.getTeam().getTeamName(),
                swimmer.getClientCode()


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
            swimmer.setRepresentorName(swimmerDto.getRepresentorName());
            swimmer.setRepresentorSurename(swimmerDto.getRepresentorSurename());
            swimmer.setPhone(swimmerDto.getPhone());
            swimmer.setClientCode(swimmerDto.getDocumentNumber());
        return swimmer;
    }
}
