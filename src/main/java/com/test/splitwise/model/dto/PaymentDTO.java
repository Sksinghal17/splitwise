package com.test.splitwise.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

  private Integer id;
  private BigDecimal amount;
  private Date date;
  private Integer payerId;
  private Integer payeeId;
  private String description;
}
