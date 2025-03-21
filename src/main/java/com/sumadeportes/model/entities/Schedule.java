package com.sumadeportes.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="schedules")
public class Schedule implements Serializable {
    @Serial
    private static final long serialVersionUID = -4793425799251072393L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
   // @ManyToOne
    //@JoinColumns({
            //@JoinColumn(name="swimmer_document_type", referencedColumnName = "documentType"),
           // @JoinColumn(name="swimmer_document_number", referencedColumnName = "documentNumber")
    //})
    //private Swimmer swimmerId;

    private String day;

    private LocalTime time;

    private boolean active;

}
