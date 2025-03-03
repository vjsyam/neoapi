package com.example.springapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.springapp.entities.Transaction;
import com.example.springapp.repositories.TransactionRepository;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> getAllTransactions(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return transactionRepository.findAll(pageable);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setDate(transactionDetails.getDate());
            transaction.setDescription(transactionDetails.getDescription());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public Page<Transaction> searchTransactions(String description, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return transactionRepository.findByDescriptionContaining(description, pageable);
    }
}
