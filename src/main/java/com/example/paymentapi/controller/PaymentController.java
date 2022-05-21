package com.example.paymentapi.controller;

import com.example.paymentapi.models.Payment;
import com.example.paymentapi.models.PaymentRequest;
import com.example.paymentapi.service.PaymentService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping("/payments")
  public Payment savePayment(@Valid @RequestBody PaymentRequest paymentRequest) {
    Payment payment = paymentRequest.toDomain();
    paymentService.savePayment(payment);
    return payment;
  }
}
