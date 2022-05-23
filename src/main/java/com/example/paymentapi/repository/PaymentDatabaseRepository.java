package com.example.paymentapi.repository;

import com.example.paymentapi.models.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDatabaseRepository extends JpaRepository<Payment, String> {


  List<Payment> findByDebtorIban(String iban);
}
