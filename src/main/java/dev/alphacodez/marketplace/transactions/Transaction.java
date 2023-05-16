package dev.alphacodez.marketplace.transactions;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private String paymentId;
    private Date date;

    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum status;
}
