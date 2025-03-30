package com.example.springapp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springapp.entities.FinancialGoal;
import com.example.springapp.services.FinancialGoalService;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/goals")
public class FinancialGoalController {
    @Autowired
    private FinancialGoalService financialGoalService;
    @GetMapping
    public List<FinancialGoal> getAllFinancialGoals() {
        return financialGoalService.getAllFinancialGoals();
    }
    @GetMapping("/{userId}")
    public List<FinancialGoal> getFinancialGoalsByUserId(@PathVariable Long userId) {
        return financialGoalService.getFinancialGoalsByUserId(userId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FinancialGoal> getFinancialGoalById(@PathVariable Long id) {
        Optional<FinancialGoal> goal = financialGoalService.getFinancialGoalById(id);
        return goal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public FinancialGoal createFinancialGoal(@RequestBody FinancialGoal financialGoal) {
        return financialGoalService.createFinancialGoal(financialGoal);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FinancialGoal> updateFinancialGoal(@PathVariable Long id, @RequestBody FinancialGoal goalDetails) {
        try {
            return ResponseEntity.ok(financialGoalService.updateFinancialGoal(id, goalDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialGoal(@PathVariable Long id) {
        financialGoalService.deleteFinancialGoal(id);
        return ResponseEntity.noContent().build();
    }
}
