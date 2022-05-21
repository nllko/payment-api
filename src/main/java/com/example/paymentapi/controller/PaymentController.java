package com.example.paymentapi.controller;

import com.example.paymentapi.models.Payment;
import com.example.paymentapi.models.PaymentRequest;
import com.example.paymentapi.service.PaymentService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping("/payments")
  @ResponseStatus(HttpStatus.CREATED)
  public Payment savePayment(@Valid @RequestBody PaymentRequest paymentRequest) {
    Payment payment = paymentRequest.toDomain();
    paymentService.savePayment(payment);
    return payment;
  }

  @PostMapping("/payment-files")
  @ResponseStatus(HttpStatus.CREATED)
  public String savePaymentsFromCsv(@RequestParam("file") MultipartFile file) throws Exception {
    CsvParserSettings settings = new CsvParserSettings();
    settings.setHeaderExtractionEnabled(true);
    CsvParser parser = new CsvParser(settings);
    parser.parseAllRecords(file.getInputStream()).forEach(record -> {
      PaymentRequest paymentRequest = new PaymentRequest(
          record.getString("amount"),
          record.getString("debtorIban"));
      paymentService.savePayment(paymentRequest.toDomain());
    });
    return "success";
  }

  @GetMapping("/payments")
  public List<Payment> getPayments(@RequestParam Optional<String> debtorIban) {
    if (debtorIban.isPresent()) {
      return paymentService.getPaymentsByIban(debtorIban.get());
    } else {
      return paymentService.getAllPayments();
    }
  }
}
