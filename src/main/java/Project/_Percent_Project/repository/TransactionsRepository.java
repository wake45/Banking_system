package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Transactions;

import java.util.Date;
import java.util.List;

public interface TransactionsRepository {
    List<Transactions> findTransactions(String accountNumber, String startDate, String endDate, String transactionType, String sortOrder); //거래내역조회
    Transactions save(Transactions transactions); //이체 정보 저장
}
