package com.sumadeportes.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tournaments")
public class Tournament implements Serializable {
    @Serial
    private static final long serialVersionUID = 153575889895207538L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String tournamentName;
   private LocalDate startDate;
   private LocalDate endDate;

 // @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
  //  private List<Event> events;


    public Tournament(String tournament, LocalDate startDate, LocalDate endDate) {
        this.tournamentName = tournament;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
