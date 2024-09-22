package com.fjb.sunrise.models;

import com.fjb.sunrise.enums.ETrans;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction extends AuditEntity<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(columnDefinition = "DECIMAL(11,2)")
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private ETrans transactionType;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;

    @OneToOne
    private Category category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
