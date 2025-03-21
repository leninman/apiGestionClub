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
public class respDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4513404818154051220L;
    private String message;
    private String code;
    private Object data;




}
