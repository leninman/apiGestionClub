package com.sumadeportes.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tests")
public class Test implements Serializable {
    @Serial
    private static final long serialVersionUID = 6086718622485973132L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private Integer startAge;
    private Integer endAge;
    private Integer length;
    private String style;
   // @OneToOne(mappedBy = "test") // Relación inversa en `Test`
   // private Event event;
    private String description;
}
