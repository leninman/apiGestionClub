package com.sumadeportes.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="levels")
public class Level implements Serializable {
    @Serial
    private static final long serialVersionUID = -7233184402286162859L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String levelName;
    //@OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Swimmer> swimmers;



}
