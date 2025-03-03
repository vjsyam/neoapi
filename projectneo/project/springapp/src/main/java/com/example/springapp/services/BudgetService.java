package com.example.springapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.entities.Budget;
import com.example.springapp.repositories.BudgetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    public Budget addBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget updateBudget(Long id, Budget budgetDetails) {
        return budgetRepository.findById(id).map(budget -> {
            budget.setAmount(budgetDetails.getAmount());
            budget.setDescription(budgetDetails.getDescription());
            return budgetRepository.save(budget);
        }).orElseThrow(() -> new RuntimeException("Budget not found"));
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
