package Project._Percent_Project.service;

import Project._Percent_Project.domain.Transactions;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    @Transactional
    @Rollback(true)
    void findById() {
        //repository랑 테스트 동일
    }

    @Test
    @Transactional
    @Rollback(true)
    void transfer() {
        //withdraw & deposit 메소드에서 테스트
    }

    @Test
    @Transactional
    @Rollback(true)
    void withdraw() {
        Transactions transactions = new Transactions();
        transactions.setAccountNumber("10011001");
        transactions.setTransactionAmount(1);
        transactions.setAccountBalance(999992);
        transactions.setTransactionType("01");
        transactions.setDepositId("002");
        transactions.setWithdrawId("001");
        transactions.setMemo("Unit test");
        transactions.setTransactionDate("20241115");

        Optional<Integer> result = accountService.withdraw(transactions, "1004");
        assertThat(result.get()).isGreaterThan(980000); //약 99만원
    }

    @Test
    @Transactional
    @Rollback(true)
    void deposit() {
        Transactions transactions = new Transactions();
        transactions.setAccountNumber("10011002");
        transactions.setTransactionAmount(1);
        transactions.setAccountBalance(100);
        transactions.setTransactionType("02");
        transactions.setDepositId("001");
        transactions.setWithdrawId("002");
        transactions.setMemo("Unit test");
        transactions.setTransactionDate("20241115");

        Optional<Integer> result = accountService.deposit(transactions);
        assertThat(result.get()).isGreaterThan(0); //약 0원
    }

    @Test
    @Transactional
    @Rollback(true)
    void validateDepositAccount() {
        Transactions transactions = new Transactions();
        transactions.setAccountNumber("10011002");

        Optional<String> result = accountService.validateDepositAccount(transactions);
        assertThat(result.get()).isEqualTo("002");
    }
}