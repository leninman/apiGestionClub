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
public class OutOfCategoryRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5482602909972005013L;
    private String gender;
    private Integer age;
    private Integer month;
}
