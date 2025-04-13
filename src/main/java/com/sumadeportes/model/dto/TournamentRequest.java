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
public class TournamentRequest implements Serializable{
    @Serial
    private static final long serialVersionUID = 123456789L;
    private int month;
    private String gender;
    private int age;


}
