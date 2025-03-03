package com.example.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springapp.entities.Income;
import com.example.springapp.services.IncomeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getAllIncome() {
        return incomeService.getAllIncome();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        Optional<Income> income = incomeService.getIncomeById(id);
        return income.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public List<Income> addIncome(@RequestBody List<Income> incomes) {
        return incomeService.addIncomes(incomes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income incomeDetails) {
        try {
            return ResponseEntity.ok(incomeService.updateIncome(id, incomeDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}