package com.example.paymentapi.service;

import com.example.paymentapi.models.Payment;
import com.example.paymentapi.repository.PaymentDatabaseRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  private final PaymentDatabaseRepository paymentDatabaseRepository;

  public PaymentService(PaymentDatabaseRepository paymentDatabaseRepository) {
    this.paymentDatabaseRepository = paymentDatabaseRepository;
  }

  public void savePayment(Payment payment) {
    paymentDatabaseRepository.save(payment);
  }
}
