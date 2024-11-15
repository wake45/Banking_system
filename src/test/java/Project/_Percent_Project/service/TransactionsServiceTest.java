package Project._Percent_Project.service;

import Project._Percent_Project.repository.TransactionsRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class TransactionsServiceTest {

    @Autowired
    TransactionsRepository transactionsRepository;

    @Test
    @Transactional
    @Rollback(true)
    void findTransactions() {
        //repository랑 테스트 동일
    }

    @Test
    @Transactional
    @Rollback(true)
    void saveTransaction() {
        //repository랑 테스트 동일
    }
}