package com.example.springapp.entities;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "financial_goals")
public class FinancialGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String goalName;
    @Column(nullable = false)
    private BigDecimal targetAmount;
    @Column(nullable = false)
    private BigDecimal currentAmount;
    @Column(nullable = false)
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public FinancialGoal() {
    }
    public FinancialGoal(Long id, String goalName, BigDecimal targetAmount, BigDecimal currentAmount, LocalDate deadline, User user) {
        this.id = id;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline = deadline;
        this.user = user;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGoalName() {
        return goalName;
    }
    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }
    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
