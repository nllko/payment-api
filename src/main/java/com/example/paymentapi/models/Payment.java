package com.example.paymentapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {

  @Id
  @Column(name = "ID")
  private String id;
  
  @Column(name = "AMOUNT")
  private BigDecimal amount;

  @Column(name = "DEBTOR_IBAN")
  private String debtorIban;

  @Column(name = "CREATED_AT")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime createdAt;

  public Payment(String id, BigDecimal amount, String debtorIban, LocalDateTime createdAt) {
    this.id = id;
    this.amount = amount;
    this.debtorIban = debtorIban;
    this.createdAt = createdAt;
  }

  public Payment() {

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getDebtorIban() {
    return debtorIban;
  }

  public void setDebtorIban(String debtorIban) {
    this.debtorIban = debtorIban;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
