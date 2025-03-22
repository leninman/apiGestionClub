package com.sumadeportes.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -9151570187083095852L;

    @EmbeddedId
    private PersonId userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Integer numberRetries;
    private Boolean locked;
    private Boolean enabled;
    private Boolean firstTime;
    private String role;




    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "documentType", referencedColumnName = "documentType"),
            @JoinColumn(name = "documentNumber", referencedColumnName = "documentNumber")
    })
    private Swimmer swimmer;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   // @JoinTable(name = "user_roles",
           // joinColumns = {
            //@JoinColumn(name = "doc_Type", referencedColumnName = "documentType"),
           // @JoinColumn(name = "doc_Number", referencedColumnName = "documentNumber")
           // },
          //  inverseJoinColumns = @JoinColumn(name = "role_id"))
   // private List<RoleEntity> roles = new ArrayList<>();





}
