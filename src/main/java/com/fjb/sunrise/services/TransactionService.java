package com.fjb.sunrise.services;

import com.fjb.sunrise.dtos.requests.TransactionCreateDTO;
import com.fjb.sunrise.dtos.responses.TransactionResponseDTO;
import java.util.List;

public interface TransactionService {
    TransactionResponseDTO createTransaction(TransactionCreateDTO transactionCreateDTO);

    TransactionResponseDTO getTransactionById(Long id);

    List<TransactionResponseDTO> getAllTransactions();

    TransactionResponseDTO updateTransaction(Long id, TransactionCreateDTO transactionCreateDTO);
}
