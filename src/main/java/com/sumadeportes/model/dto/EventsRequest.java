package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventsRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -6554113027428042688L;
    private Integer month;
    private String gender;
    private int age;


}
