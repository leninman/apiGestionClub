package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -385078992205212237L;

    private String gender;
    private Integer startAge;
    private Integer endAge;
    private Integer length;
    private String style;
    private String tournamentName;
    private Integer eventNumber;
    private String eventName;
    private String startDate;
    private String endDate;



}
