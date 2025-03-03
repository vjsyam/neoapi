package com.example.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springapp.entities.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
