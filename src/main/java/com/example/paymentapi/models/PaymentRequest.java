package com.example.paymentapi.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class PaymentRequest {

  @NotBlank
  @Positive
  private String amount;
  @NotBlank
  @Pattern(regexp = "^(LV\\d{2}[A-Z]{4}\\d{13})|((?:EE|LT)\\d{18})$")
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
