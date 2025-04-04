package com.sumadeportes.model.dto;

import com.sumadeportes.model.entities.PersonId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -2357682746057218600L;
    private PersonId swimmerId;
    private String teamDescription;

}
