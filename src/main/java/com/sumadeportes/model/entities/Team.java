package com.sumadeportes.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="teams")
public class Team implements Serializable {
    @Serial
    private static final long serialVersionUID = 3089791279749149601L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<TournamentTeam> tournamentTeams=new ArrayList<>();

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Swimmer> swimmers=new ArrayList<>();






}
