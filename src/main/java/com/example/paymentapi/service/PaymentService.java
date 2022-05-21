package com.example.paymentapi.service;

import com.example.paymentapi.models.Payment;
import com.example.paymentapi.repository.PaymentDatabaseRepository;
import java.util.List;
import java.util.Optional;
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

  public List<Payment> getAllPayments() {
    return paymentDatabaseRepository.findAll();
  }

  public List<Payment> getPaymentsByIban(String iban) {
    return paymentDatabaseRepository.findAll().stream()
        .filter(payment -> payment.getDebtorIban().equalsIgnoreCase(iban))
        .toList();
  }
}
