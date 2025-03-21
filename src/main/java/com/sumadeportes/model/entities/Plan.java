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
@Table(name="plains")
public class Plan implements Serializable {
    @Serial
    private static final long serialVersionUID = -7669476116969378647L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String planName;
    //@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Swimmer> swimmers;




}
