package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSheet implements Serializable {
    @Serial
    private static final long serialVersionUID = 7438617170991761374L;
    private String tournament;
    private String teamName;
    private List<EventRegisterResponse> eventRegisterResponses= new ArrayList<>();

}
