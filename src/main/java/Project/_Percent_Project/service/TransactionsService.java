package Project._Percent_Project.service;

import Project._Percent_Project.domain.Transactions;
import Project._Percent_Project.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    /**
     * 거래내역 조회
     */
    public List<Transactions> findTransactions(String accountNumber, Date startDate, Date endDate, String transactionType, String sortOrder){
        return transactionsRepository.findTransactions(accountNumber, startDate, endDate, transactionType, sortOrder);
    }

    /**
     * 거래내역 저장
     */
    public Long saveTransaction(Transactions transactions) {
        transactionsRepository.save(transactions);
        return transactions.getTransactionId();
    }
}
