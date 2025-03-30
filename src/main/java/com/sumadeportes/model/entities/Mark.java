package com.sumadeportes.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "marks")
public class Mark implements Serializable {
    @Serial
    private static final long serialVersionUID = -7954003939194965539L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="swimmer_document_type", referencedColumnName = "documentType"),
            @JoinColumn(name="swimmer_document_number", referencedColumnName = "documentNumber")
    })
    private Swimmer swimmer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private Float mark;



}
