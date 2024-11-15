package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JpaAccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    @Transactional
    @Rollback(true)
    void findById() {
        //카카오뱅크 계정 보유 계좌 10011002 조회
        List<Account> result = accountRepository.findById("002");
        assertThat(result.get(0).getAccountNumber()).isEqualTo("10011002");
        //assertThat(result.get(0).getAccountNumber()).isEqualTo("10011001");
    }

    @Test
    @Transactional
    @Rollback(true)
    void findId() {
        Optional<String> result = accountRepository.findId("10011002");
        assertThat(result.get()).isEqualTo("002");
        //assertThat(result.get()).isEqualTo("001");
    }

    @Test
    @Transactional
    @Rollback(true)
    void findPassword() {
        Optional<String> result = accountRepository.findPassword("10011001");
        assertThat(result.get()).isEqualTo("1004");
        //assertThat(result.get()).isEqualTo("1005");
    }

    @Test
    @Transactional
    @Rollback(true)
    void findBalance() {
        Optional<Integer> result = accountRepository.findBalance("10011003"); //약 1만원
        assertThat(result.get()).isGreaterThan(9000);
        assertThat(result.get()).isLessThan(20000);
    }

    @Test
    @Transactional
    @Rollback(true)
    void updateAccount() {
        //insert test data ('10011001', '001', 999999, '1004', '01', '20241112', '20241112')
        Transactions transactions = new Transactions();
        transactions.setAccountNumber("10011001");
        transactions.setAccountBalance(1);
        transactions.setTransactionDate("20241115");

        Optional<Integer> result = accountRepository.updateAccount(transactions);
        assertThat(result.get()).isGreaterThan(980000); //약 99만원
    }
}