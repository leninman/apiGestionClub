package com.sumadeportes.model.dto;

import com.sumadeportes.model.entities.PersonId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -6098035080741611202L;
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
}
