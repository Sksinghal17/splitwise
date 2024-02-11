package com.test.splitwise.service;

import com.test.splitwise.converter.ExpenseConverter;
import com.test.splitwise.model.dto.ExpenseDTO;
import com.test.splitwise.model.entity.Expense;
import com.test.splitwise.model.entity.User;
import com.test.splitwise.repos.ExpenseRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseService {

  @Autowired
  private ExpenseRepository expenseRepository;

  @Autowired
  private ExpenseConverter expenseConverter;

  public Expense createExpense(ExpenseDTO expenseDTO) {
    Expense expense = expenseConverter.toEntity(expenseDTO);
    return expenseRepository.save(expense);
  }

  public Expense updateExpense(Integer id, ExpenseDTO expenseDTO) {
    Expense existingExpense = expenseRepository.findById(id).orElse(null);
    if (existingExpense != null) {
      expenseConverter.updateEntityFromDTO(expenseDTO, existingExpense);
      return expenseRepository.save(existingExpense);
    }
    return null;
  }

  public boolean deleteExpense(Integer id) {
    Expense existingExpense = expenseRepository.findById(id).orElse(null);
    if (existingExpense != null) {
      expenseRepository.delete(existingExpense);
      return true;
    }
    return false;
  }

  public Map<String, Double> calculateFinalExpenseResult() {
    Map<String, Double> finalExpenseResult = new HashMap<>();

    List<Expense> expenses = expenseRepository.findAll();

    // Calculate total expenses for each user
    Map<String, Double> totalExpensesByUser = new HashMap<>();
    for (Expense expense : expenses) {
      String payer = expense.getPayer().getName();
      Double amount = expense.getAmount().doubleValue();
      totalExpensesByUser.put(payer, totalExpensesByUser.getOrDefault(payer, 0.0) + amount);
    }

    // Calculate total contributions for each user
    Map<String, Double> totalContributionsByUser = new HashMap<>();
    for (Expense expense : expenses) {
      List<User> participants = expense.getParticipants();
      List<String> contributors = participants.stream().map(User::getName).toList();
      Double amountPerUser = expense.getAmount().doubleValue() / contributors.size();
      for (String contributor : contributors) {
        totalContributionsByUser.put(contributor,
            totalContributionsByUser.getOrDefault(contributor, 0.0) + amountPerUser);
      }
    }

    // Calculate the final balance for each user
    for (String user : totalExpensesByUser.keySet()) {
      Double totalExpense = totalExpensesByUser.get(user);
      Double totalContribution = totalContributionsByUser.getOrDefault(user, 0.0);
      finalExpenseResult.put(user, totalContribution - totalExpense);
    }

    return finalExpenseResult;
  }
}

