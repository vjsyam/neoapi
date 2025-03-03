package com.example.springapp.services;

import com.example.springapp.entities.Income;
import com.example.springapp.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    public Optional<Income> getIncomeById(Long id) {
        return incomeRepository.findById(id);
    }

    public List<Income> addIncomes(List<Income> incomes) {
        return incomeRepository.saveAll(incomes);
    }

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public Income updateIncome(Long id, Income incomeDetails) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new RuntimeException("Income not found"));
        income.setAmount(incomeDetails.getAmount());
        income.setSource(incomeDetails.getSource());
        return incomeRepository.save(income);
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}