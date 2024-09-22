package com.fjb.sunrise.mappers;

import com.fjb.sunrise.dtos.requests.TransactionCreateDTO;
import com.fjb.sunrise.dtos.responses.TransactionResponseDTO;
import com.fjb.sunrise.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toTransaction(TransactionCreateDTO transactionCreateDTO);

    TransactionResponseDTO toTransactionResponseDTO(Transaction transaction);

    Transaction updateTransaction(@MappingTarget Transaction transaction, TransactionCreateDTO transactionCreateDTO);
}
