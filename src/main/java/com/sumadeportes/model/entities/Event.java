package com.sumadeportes.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="events")
public class Event implements Serializable {
    @Serial
    private static final long serialVersionUID = -8101306775713001036L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;
   @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
   private Tournament tournament;

    @OneToOne(cascade = CascadeType.ALL) // Un evento tiene un test
    @JoinColumn(name = "test_id", unique = true, nullable = false)
    private Test test;

    private Integer eventNumber;
}
