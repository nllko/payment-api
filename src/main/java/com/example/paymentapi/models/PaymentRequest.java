package com.example.paymentapi.models;

import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentRequest {

  @NotNull
  private String amount;
  @NotNull
  private String debtorIban;

  public PaymentRequest(String amount, String debtorIban) {
    this.amount = amount;
    this.debtorIban = debtorIban;
  }

  public Payment toDomain(){
    String id = String.valueOf(UUID.randomUUID());
    BigDecimal bigDecimalAmount = new BigDecimal(this.amount);
    LocalDateTime currentTime = LocalDateTime.now();
    return new Payment(id,bigDecimalAmount,debtorIban,currentTime);
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getDebtorIban() {
    return debtorIban;
  }

  public void setDebtorIban(String debtorIban) {
    this.debtorIban = debtorIban;
  }
}
