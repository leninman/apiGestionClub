package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventsResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -737013171000273093L;

    private Integer date;
    private List<String> eventName;
    private List<String> tournamentsName;

}
