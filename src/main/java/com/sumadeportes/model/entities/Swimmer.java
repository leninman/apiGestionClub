package com.sumadeportes.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private String birthDate;
    private String incomingDate;
    private String email;
    private String swimmerKey;
    private String status;
    private Integer yearsActive;
    private Integer monthsActive;
    private Integer daysActive;
    private String representorName;
    private String representorSurename;
    private String phone;
    private String plain;
    private String level;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
   // private String inactive;



    /*@ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;*/
   // @ManyToOne
 //   @JoinColumns({
           // @JoinColumn(name="representor_document_type", referencedColumnName = "documentType"),
           // @JoinColumn(name="representor_document_number", referencedColumnName = "documentNumber")
   // })
   // private Representor representor;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

   // @OneToMany(mappedBy = "swimmerId", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Schedule> schedules;

  //  @JsonIgnore
   // @OneToOne(mappedBy = "swimmer",cascade = CascadeType.ALL)
 //   private UserEntity user;







}
