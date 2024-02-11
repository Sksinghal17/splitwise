package com.test.splitwise.model.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String type;
  private Date date;
  @ManyToOne
  private User user;
  @ManyToOne
  private Group group;
  @ManyToOne
  private Expense expense;
  @ManyToOne
  private Payment payment;

}
