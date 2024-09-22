package com.fjb.sunrise.dtos.requests;

import com.fjb.sunrise.enums.ETrans;
import com.fjb.sunrise.models.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCreateDTO {

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private ETrans transactionType;

    private String note;

    private Category category;
}
