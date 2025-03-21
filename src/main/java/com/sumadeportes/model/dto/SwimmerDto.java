package com.sumadeportes.model.dto;

import com.sumadeportes.model.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SwimmerDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6531956400440558837L;
    private String documentType;
    private String documentNumber;
    private String firstName;
    private String secondName;
    private String firstSurename;
    private String secondSurename;
    private Integer age;
    private String gender;
    private String birthDate;
    private String incomingDate;
    private String email;
    private String swimmerKey;
    private String status;
    private Integer yearsActive;
    private Integer monthsActive;
    private Integer daysActive;
    private String representorName;
    private String representorSurename;
    private String phone;
    private String plain;
    private String level;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String team;
    
    
}
