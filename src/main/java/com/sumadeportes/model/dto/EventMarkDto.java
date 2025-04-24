package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventMarkDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529721085450373355L;
    private String eventName;
    private String eventNumber;
    private String mark;
}
