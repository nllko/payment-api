package com.example.paymentapi.controller;

import com.example.paymentapi.models.Payment;
import com.example.paymentapi.models.PaymentRequest;
import com.example.paymentapi.service.PaymentService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
  public void savePayment(@Valid @RequestBody PaymentRequest paymentRequest,
      HttpServletRequest httpServletRequest) {
    paymentService.savePayment(paymentRequest, httpServletRequest);
  }

  @PostMapping("/payment-files")
  @ResponseStatus(HttpStatus.CREATED)
  public void savePaymentsFromCsv(@RequestParam("file") MultipartFile file,
      HttpServletRequest request) throws IOException {
    paymentService.savePaymentFromCsv(file, request);
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
