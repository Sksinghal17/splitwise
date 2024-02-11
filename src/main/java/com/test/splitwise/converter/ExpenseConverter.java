package com.test.splitwise.converter;

import com.test.splitwise.model.dto.ExpenseDTO;
import com.test.splitwise.model.entity.Expense;
import com.test.splitwise.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseConverter {

  @Autowired
  private ModelMapper modelMapper;

  public ExpenseDTO toDto(Expense expense) {
    return modelMapper.map(expense, ExpenseDTO.class);
  }

  public Expense toEntity(ExpenseDTO dto) {
    return modelMapper.map(dto, Expense.class);
  }

  public Expense updateEntityFromDTO(ExpenseDTO expenseDTO, Expense existingExpense) {
    existingExpense.setDescription(expenseDTO.getDescription());
    existingExpense.setAmount(expenseDTO.getAmount());
    existingExpense.setCurrency(expenseDTO.getCurrency());
    existingExpense.setPayer(User.builder().id(expenseDTO.getPayerId()).build());
    existingExpense.setDate(expenseDTO.getDate());

    // Return the updated entity
    return existingExpense;
  }

}
