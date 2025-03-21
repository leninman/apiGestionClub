package com.sumadeportes.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="representors")
public class Representor implements Serializable {
    @Serial
    private static final long serialVersionUID = 433974273451381476L;
   @EmbeddedId
   private PersonId representorId;
    private String firstName;
    private String secondName;
    private String firstSurename;
    private String secondSurename;
    private String email;
    private String phone;
  //  @OneToMany(mappedBy = "representor", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<Swimmer> swimmers;




}
