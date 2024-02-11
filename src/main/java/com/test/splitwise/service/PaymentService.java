package com.test.splitwise.service;

import com.test.splitwise.converter.PaymentConverter;
import com.test.splitwise.exception.SplitwiseException;
import com.test.splitwise.model.dto.PaymentDTO;
import com.test.splitwise.model.entity.Payment;
import com.test.splitwise.repos.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private PaymentConverter paymentConverter;

  public PaymentDTO makePayment(PaymentDTO paymentDTO) {
    Payment payment = paymentConverter.toEntity(paymentDTO);
    Payment savedPayment = paymentRepository.save(payment);
    return paymentConverter.toDto(savedPayment);
  }

  public PaymentDTO getPaymentById(Integer paymentId) {
    Payment payment = paymentRepository.findById(paymentId)
        .orElseThrow(() -> new SplitwiseException("Payment not found with id: " + paymentId));
    return paymentConverter.toDto(payment);
  }

}

