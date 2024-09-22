package com.fjb.sunrise.services.impl;

import com.fjb.sunrise.dtos.requests.TransactionCreateDTO;
import com.fjb.sunrise.dtos.responses.TransactionResponseDTO;
import com.fjb.sunrise.exceptions.NotFoundException;
import com.fjb.sunrise.mappers.TransactionMapper;
import com.fjb.sunrise.models.Transaction;
import com.fjb.sunrise.models.User;
import com.fjb.sunrise.repositories.TransactionRepository;
import com.fjb.sunrise.repositories.UserRepository;
import com.fjb.sunrise.services.TransactionService;
import com.fjb.sunrise.utils.Constants;
import com.fjb.sunrise.utils.auth.AuthUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;
    private final AuthUtil authUtils;

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {
        Transaction transaction = transactionMapper.toTransaction(transactionCreateDTO);
        User user = authUtils.getUser();
        transaction.setUser(user);
        user.addTransaction(transaction);
        userRepository.save(user);
        return transactionMapper.toTransactionResponseDTO(transaction);
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        return transactionRepository.findById(id)
            .map(transactionMapper::toTransactionResponseDTO)
            .orElseThrow(() -> new NotFoundException(Constants.ErrorCode.TRANSACTION_NOT_FOUND));
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
            .map(transactionMapper::toTransactionResponseDTO)
            .toList();
    }

    @Override
    public TransactionResponseDTO updateTransaction(Long id, TransactionCreateDTO transactionCreateDTO) {
        return null;
    }
}
