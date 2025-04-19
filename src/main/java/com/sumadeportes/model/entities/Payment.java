package com.sumadeportes.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String paymentMedium;
    private String orgBank;
    private Double paymentAmount;
    private String paymentReference;
    private LocalDate paymentDate;
    private String celNumber;
    private String email;
    private String orgAccount;
    private String destAccount;
    private String docType;
    private String docNumber;
    private LocalDate createdAt;
    private String fileName;
    @Lob
    private byte[] paymentVoucher;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }
}
