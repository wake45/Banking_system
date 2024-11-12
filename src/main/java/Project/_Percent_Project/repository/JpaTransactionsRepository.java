package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

public class JpaTransactionsRepository implements TransactionsRepository {

    private final EntityManager em;

    public JpaTransactionsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Transactions> findTransactions(String accountNumber, Date startDate, Date endDate, String Month, int TransactionType, int sortOrder) {
        String queryString = "SELECT t FROM Transactions t WHERE t.accountNumber = :accountNumber " +
                "AND t.date BETWEEN :startDate AND :endDate " +
                "AND t.transactionType = :transactionType";

        return queryString.getResultList();
    }

    @Override
    public Transactions save(Transactions transactions) {
        em.persist(transactions);
        return transactions;
    }
}
