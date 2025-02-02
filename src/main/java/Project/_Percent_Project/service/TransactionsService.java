package Project._Percent_Project.service;

import Project._Percent_Project.domain.Transactions;
import Project._Percent_Project.repository.TransactionsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    /**
     * 거래내역 조회
     */
    public Page<Transactions> findTransactions(String accountNumber, String startDate, String endDate, String transactionType, String sortOrder, Pageable pageable) {
        return transactionsRepository.findTransactions(accountNumber, startDate, endDate, transactionType, sortOrder, pageable);
    }

    /**
     * 거래내역 저장
     */
    public Long saveTransaction(Transactions transactions) {
        transactionsRepository.save(transactions);
        return transactions.getTransactionId();
    }
}
