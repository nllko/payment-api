package com.example.paymentapi.service;

import com.example.paymentapi.models.Payment;
import com.example.paymentapi.models.PaymentRequest;
import com.example.paymentapi.repository.PaymentDatabaseRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PaymentService {

  private final PaymentDatabaseRepository paymentDatabaseRepository;

  public PaymentService(PaymentDatabaseRepository paymentDatabaseRepository) {
    this.paymentDatabaseRepository = paymentDatabaseRepository;
  }

  public void savePayment(PaymentRequest paymentRequest, HttpServletRequest request) {
    Payment payment = paymentRequest.toDomain();
    payment.setCallerCountry(getCountryCode(request));
    paymentDatabaseRepository.save(payment);
  }

  public void savePaymentFromCsv(MultipartFile file, HttpServletRequest request)
      throws IOException {
    List<Payment> payments = new ArrayList<>();
    CsvParserSettings settings = new CsvParserSettings();
    settings.setHeaderExtractionEnabled(true);
    CsvParser parser = new CsvParser(settings);
    parser.parseAllRecords(file.getInputStream()).forEach(record -> {
      PaymentRequest paymentRequest = new PaymentRequest(
          record.getString("amount"),
          record.getString("debtorIban"));
      Payment payment = paymentRequest.toDomain();
      payment.setCallerCountry(getCountryCode(request));
      payments.add(payment);
    });
    paymentDatabaseRepository.saveAll(payments);
  }

  public List<Payment> getAllPayments() {
    return paymentDatabaseRepository.findAll();
  }

  public List<Payment> getPaymentsByIban(String iban) {
    return paymentDatabaseRepository.findByDebtorIban(iban);
  }

  public String getCountryCode(HttpServletRequest httpServletRequest) {
    String ipAddress = httpServletRequest.getHeader("X-Forwarded-For");
    final String uri =
        "http://ip-api.com/json/" + ipAddress + "?fields=country";
    RestTemplate restTemplate = new RestTemplate();
    JSONObject jsonObject = new JSONObject(restTemplate.getForObject(uri, String.class));
    return jsonObject.isEmpty() ? "" : jsonObject.getString("country");
  }
}
