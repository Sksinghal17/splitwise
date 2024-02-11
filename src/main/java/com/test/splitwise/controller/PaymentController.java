package com.test.splitwise.controller;

import com.test.splitwise.model.dto.PaymentDTO;
import com.test.splitwise.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  // Endpoint to make a payment
  @PostMapping
  public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO) {
    PaymentDTO createdPayment = paymentService.makePayment(paymentDTO);
    return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
  }

  // Endpoint to get payment details by ID
  @GetMapping("/{paymentId}")
  public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Integer paymentId) {
    PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
    return ResponseEntity.ok(paymentDTO);
  }

}

