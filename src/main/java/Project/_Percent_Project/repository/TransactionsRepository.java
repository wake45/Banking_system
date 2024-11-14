package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionsRepository {
    Page<Transactions> findTransactions(String accountNumber, String startDate, String endDate, String transactionType, String sortOrder, Pageable pageable); //거래내역조회
    Transactions save(Transactions transactions); //이체 정보 저장
}
