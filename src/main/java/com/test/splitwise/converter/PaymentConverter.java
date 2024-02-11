package com.test.splitwise.converter;

import com.test.splitwise.model.dto.PaymentDTO;
import com.test.splitwise.model.entity.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {

  @Autowired
  private ModelMapper modelMapper;

  public PaymentDTO toDto(Payment payment) {
    return modelMapper.map(payment, PaymentDTO.class);
  }

  public Payment toEntity(PaymentDTO dto) {
    return modelMapper.map(dto, Payment.class);
  }
}
