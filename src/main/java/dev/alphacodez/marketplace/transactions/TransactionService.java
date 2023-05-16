package dev.alphacodez.marketplace.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public void createTransaction(String transactionId, String paymentId) {
        try{
            var transaction = Transaction.builder()
                    .transactionId(transactionId)
                    .paymentId(paymentId)
                    .status(TransactionStatusEnum.ORDERED)
                    .date(new Date())
                    .build();
            transactionRepository.save(transaction);
        }catch(Exception e){
            throw new IllegalStateException(e.getMessage());
        }

    }

    public List<Transaction> fetchAllTransactions() {
        return transactionRepository.findAll();
    }
}
