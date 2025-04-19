package com.sumadeportes.services;

import com.sumadeportes.model.dto.PaymentRequest;
import com.sumadeportes.model.entities.Payment;
import com.sumadeportes.model.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class IPaymentServiceImpl implements IPaymentService {

     private final PaymentRepository paymentRepository;

    public IPaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment savePayment(PaymentRequest paymentRequest) throws IOException {

        Payment p= paymentRepository.findPaymentByPaymentReference(paymentRequest.getPaymentReference());
        if(p!=null){
            return null;
        }

        Payment payment = new Payment();

        if(paymentRequest.getPaymentMedium().equals("PM")){
            payment.setPaymentMedium("PM");
            payment.setOrgBank(paymentRequest.getOrgBank());
            payment.setPaymentAmount(paymentRequest.getPaymentAmount());
            payment.setPaymentReference(paymentRequest.getPaymentReference());
            payment.setCelNumber(paymentRequest.getCelNumber());
            payment.setPaymentDate(paymentRequest.getPaymentDate());
        }
        if(paymentRequest.getPaymentMedium().equals("TR")){
            payment.setOrgBank(paymentRequest.getOrgBank());
            payment.setPaymentAmount(paymentRequest.getPaymentAmount());
            payment.setOrgAccount(paymentRequest.getOrgAccount());
            payment.setDestAccount(paymentRequest.getDestAccount());
            payment.setPaymentReference(paymentRequest.getPaymentReference());
            payment.setDocType(paymentRequest.getDocType());
            payment.setDocNumber(paymentRequest.getDocNumber());
            payment.setPaymentDate(paymentRequest.getPaymentDate());
        }
        if(paymentRequest.getPaymentMedium().equals("ZE")){
            payment.setEmail(paymentRequest.getEmail());
            payment.setPaymentAmount(paymentRequest.getPaymentAmount());
            payment.setPaymentDate(paymentRequest.getPaymentDate());
        }

       // payment.setFileName(file.getOriginalFilename());
       // payment.setPaymentVoucher(file.getBytes());
        return paymentRepository.save(payment);
    }

    public Payment findPaymentByPaymentReference(String paymentReference) {
        return paymentRepository.findPaymentByPaymentReference(paymentReference);
    }
}
