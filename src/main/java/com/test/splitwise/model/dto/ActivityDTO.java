package com.test.splitwise.model.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

  private Integer id;
  private String type;
  private Date date;
  private Integer userId;
  private Integer groupId;
  private Integer expenseId;
  private Integer paymentId;
}

