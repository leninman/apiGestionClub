package com.sumadeportes.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2534386559575201882L;
    private String documentType;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Boolean locked;
    private Boolean enabled;
    public Boolean firstTime;
    private Integer numberRetries;
   // private List<String> roles; // Solo los nombres de los roles
    private String role;


   
}
