package com.example.paymentapi.repository;

import com.example.paymentapi.models.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentDatabaseRepository extends JpaRepository<Payment, String> {

  @Query("select p from Payment p where p.debtorIban = :iban ")
  List<Payment> findByDebtorIban(String iban);
}
