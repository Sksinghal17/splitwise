package com.test.splitwise.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
  private Integer id;
  private String description;
  private BigDecimal amount;
  private String currency;
  private Date date;
  private Integer payerId;
  private Integer groupId;
}

