package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Transactions;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaTransactionsRepositoryTest {

    @Autowired
    TransactionsRepository  transactionsRepository;

    @Test
    @Transactional
    @Rollback(true)
    void findTransactions() {
        //insert test data ('31', '10011001', 1, 10000021, '01', '002', '001', '테스트31', '20241101')
        Transactions transactions = new Transactions();
        transactions.setTransactionId(31L);
        transactions.setAccountNumber("10011001");
        transactions.setTransactionAmount(1);
        transactions.setAccountBalance(10000021);
        transactions.setTransactionType("01");
        transactions.setDepositId("002");
        transactions.setWithdrawId("001");
        transactions.setMemo("테스트31");
        transactions.setTransactionDate("20241101");

        Pageable pageable = PageRequest.of(0, 5, Sort.by("transactionDate").ascending());
        Page<Transactions> result = transactionsRepository.findTransactions("10011001", "20241101", "20241101", "01", "ASC", pageable);

        // Transactions 값을 출력
        Transactions resultTransaction = result.getContent().get(0);
        assertThat(resultTransaction).usingRecursiveComparison().isEqualTo(transactions);
    }

    @Test
    @Transactional
    @Rollback(true)
    void save() {
        Transactions transactions = new Transactions();
        //transactions.setTransactionId(999L);
        transactions.setAccountNumber("10011001");
        transactions.setTransactionAmount(100);
        transactions.setAccountBalance(1000000);
        transactions.setTransactionType("01");
        transactions.setDepositId("002");
        transactions.setWithdrawId("001");
        transactions.setMemo("Unit test");
        transactions.setTransactionDate("20241115");

        Transactions result = transactionsRepository.save(transactions);
        assertThat(result).usingRecursiveComparison().isEqualTo(transactions);
    }
}