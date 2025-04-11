package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EndedEventsRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -6554113027428042688L;
    private String gender;
    private int age;
   // private String tournamentName;
   // private LocalDate dateToday;
  //  int monthToday;

}
