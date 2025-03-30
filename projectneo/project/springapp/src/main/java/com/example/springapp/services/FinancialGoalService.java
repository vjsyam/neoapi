package com.example.springapp.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springapp.entities.FinancialGoal;
import com.example.springapp.repositories.FinancialGoalRepository;
import java.util.List;
import java.util.Optional;
@Service
public class FinancialGoalService {
    @Autowired
    private FinancialGoalRepository financialGoalRepository;
    public List<FinancialGoal> getAllFinancialGoals() {
        return financialGoalRepository.findAll();
    }
    public List<FinancialGoal> getFinancialGoalsByUserId(Long userId) {
        return financialGoalRepository.findByUserId(userId);
    }
    public Optional<FinancialGoal> getFinancialGoalById(Long id) {
        return financialGoalRepository.findById(id);
    }
    public FinancialGoal createFinancialGoal(FinancialGoal financialGoal) {
        return financialGoalRepository.save(financialGoal);
    }
    public FinancialGoal updateFinancialGoal(Long id, FinancialGoal goalDetails) {
        return financialGoalRepository.findById(id).map(goal -> {
            goal.setGoalName(goalDetails.getGoalName());
            goal.setTargetAmount(goalDetails.getTargetAmount());
            goal.setCurrentAmount(goalDetails.getCurrentAmount());
            goal.setDeadline(goalDetails.getDeadline());
            return financialGoalRepository.save(goal);
        }).orElseThrow(() -> new RuntimeException("Financial Goal not found"));
    }
    public void deleteFinancialGoal(Long id) {
        financialGoalRepository.deleteById(id);
    }
}
