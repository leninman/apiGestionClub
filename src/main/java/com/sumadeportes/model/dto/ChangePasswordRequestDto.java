package com.sumadeportes.model.dto;

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
public class ChangePasswordRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 6691210272968556679L;
    private String email;
    private String oldPassword;
    private String newPassword;

}
