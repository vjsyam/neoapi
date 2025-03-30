package com.example.springapp.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "budget")
public class Budget {
    @Column(nullable = false)
    private Long userId; // Added user_id field

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String category;

    private String description;

    // Constructors
    public Budget() {
    }

    public Budget(BigDecimal amount, String category, String description, Long userId) {
        this.userId = userId; // Set user_id in constructor
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    // Getters and Setters
    public Long getUserId() { // Getter for user_id
        return userId;
    }

    public void setUserId(Long userId) { // Setter for user_id
        this.userId = userId;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
