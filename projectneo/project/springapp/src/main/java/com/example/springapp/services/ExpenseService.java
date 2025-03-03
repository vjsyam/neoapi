package com.example.springapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.entities.Expense;
import com.example.springapp.repositories.ExpenseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        return expenseRepository.findById(id).map(expense -> {
            expense.setAmount(expenseDetails.getAmount());
            expense.setDate(expenseDetails.getDate());
            expense.setDescription(expenseDetails.getDescription());
            return expenseRepository.save(expense);
        }).orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
