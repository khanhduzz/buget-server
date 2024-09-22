package com.fjb.sunrise.repositories;

import com.fjb.sunrise.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
