package com.example.paymentapi.service;

import com.example.paymentapi.models.Payment;
import com.example.paymentapi.repository.PaymentDatabaseRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

  public String getCountryCode(HttpServletRequest httpServletRequest) {
    String ipAddress = httpServletRequest.getHeader("X-Forwarded-For");
    final String uri =
        "http://ip-api.com/json/" + ipAddress + "?fields=country";
    RestTemplate restTemplate = new RestTemplate();
    JSONObject jsonObject = new JSONObject(restTemplate.getForObject(uri, String.class));
    return jsonObject.isEmpty()?"Failed to find country":jsonObject.getString("country");
  }
}
