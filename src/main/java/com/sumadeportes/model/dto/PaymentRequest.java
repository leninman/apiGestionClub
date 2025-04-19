package com.sumadeportes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest implements Serializable {

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

}

