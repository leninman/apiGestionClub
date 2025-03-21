package com.sumadeportes.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
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
@Embeddable
public class PersonId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1437853524398721304L;
    private String documentType;
    private String documentNumber;



}
