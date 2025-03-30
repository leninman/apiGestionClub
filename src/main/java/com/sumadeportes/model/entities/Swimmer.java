package com.sumadeportes.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="swimmers")
public class Swimmer implements Serializable {
    @Serial
    private static final long serialVersionUID = 7362938229453279267L;
    @EmbeddedId
    private PersonId swimmerId;
    private String firstName;
    private String secondName;
    private String firstSurename;
    private String secondSurename;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private LocalDate incomingDate;
    private String email;
    private String swimmerKey;
    private String status;
    private String representorName;
    private String representorSurename;
    private String phone;
    private String clientCode;


    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @OneToMany(mappedBy = "swimmer")
    @JsonManagedReference
    private List<EventRegister> eventsRegisters=new ArrayList<>();








}
