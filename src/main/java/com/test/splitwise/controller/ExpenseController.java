package com.test.splitwise.controller;

import com.test.splitwise.model.dto.ExpenseDTO;
import com.test.splitwise.model.entity.Expense;
import com.test.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

  @Autowired
  private ExpenseService expenseService;

  @PostMapping
  public ResponseEntity<Expense> createExpense(@RequestBody ExpenseDTO expenseDTO) {
    Expense createdExpense = expenseService.createExpense(expenseDTO);
    return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Expense> updateExpense(@PathVariable Integer id, @RequestBody ExpenseDTO expenseDTO) {
    Expense updatedExpense = expenseService.updateExpense(id, expenseDTO);
    if (updatedExpense != null) {
      return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
    boolean deleted = expenseService.deleteExpense(id);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
