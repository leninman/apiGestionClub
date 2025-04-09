package com.sumadeportes.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="events")
public class Event implements Serializable {
    @Serial
    private static final long serialVersionUID = -8101306775713001036L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;

    @ManyToOne

    @JoinColumn(name = "tournament_id", nullable = false)
   private Tournament tournament;

    @ManyToOne(cascade = CascadeType.ALL) // Un evento tiene un test
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "event")
    @JsonManagedReference
    private List<EventRegister> eventsRegisters=new ArrayList<>();

    private Integer eventNumber;
}
