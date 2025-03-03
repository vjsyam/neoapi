package com.example.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springapp.entities.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
