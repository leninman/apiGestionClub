package com.sumadeportes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sumadeportes.model.dto.PaymentRequest;
import com.sumadeportes.model.dto.RespDto;
import com.sumadeportes.model.entities.Payment;
import com.sumadeportes.services.IPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "https://erika-github.github.io")
public class PaymentController {
    private final IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/send")
    public ResponseEntity<RespDto> sendPmPayment(@RequestBody PaymentRequest payment) throws IOException {
        RespDto response = new RespDto();
     //   ObjectMapper mapper = new ObjectMapper();
    //    mapper.registerModule(new JavaTimeModule());
    //    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    //    PaymentRequest data = mapper.readValue(payment, PaymentRequest.class);


        Payment p=paymentService.savePayment(payment);
        if (p == null) {
            response.setMessage("There is a payment with the same number of reference");
            response.setCode("409");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }else{
            response.setMessage("Payment sent successfully");
            response.setCode("201");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }



    }
}
