package com.example.springapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springapp.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE LOWER(t.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    Page<Transaction> findByDescriptionContaining(String description, Pageable pageable);
}