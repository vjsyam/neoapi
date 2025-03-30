package com.example.springapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springapp.entities.Expense;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
