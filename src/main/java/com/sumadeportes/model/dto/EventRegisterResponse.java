package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRegisterResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String swimmerName;
    private String swimmerNumber;


    private List<String> eventsNames;


}
