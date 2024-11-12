package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Transactions;

import java.util.Date;
import java.util.List;

public interface TransactionsRepository {
    List<Transactions> findTransactions(String accountNumber, Date startDate, Date endDate, String Month, int TransactionType, int sortOrder);
    //거래내역조회
    Transactions save(Transactions transactions); //이체 정보 저장
}
