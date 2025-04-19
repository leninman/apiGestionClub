package com.sumadeportes.services;

import com.sumadeportes.model.dto.PaymentRequest;
import com.sumadeportes.model.entities.Payment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPaymentService {
    Payment savePayment(PaymentRequest paymentRequest) throws IOException;

}
