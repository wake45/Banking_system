package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Transactions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

public class JpaTransactionsRepository implements TransactionsRepository {

    private final EntityManager em;

    public JpaTransactionsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Transactions> findTransactions(String accountNumber, Date startDate, Date endDate, String transactionType, String sortOrder) {
        String queryString = "SELECT t FROM Transactions t WHERE t.accountNumber = :accountNumber " +
                             "AND t.transactionDate BETWEEN :startDate AND :endDate " +
                             "AND t.transactionType = " + transactionType +
                             "ORDER BY t.transactionType " + sortOrder;

        TypedQuery<Transactions> query = em.createQuery(queryString, Transactions.class)
                .setParameter("accountNumber", accountNumber)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("transactionType", transactionType);

        return query.getResultList();
    }

    @Override
    public Transactions save(Transactions transactions) {
        em.persist(transactions);
        return transactions;
    }
}
