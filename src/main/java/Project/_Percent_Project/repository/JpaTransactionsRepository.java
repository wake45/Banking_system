package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Transactions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTransactionsRepository implements TransactionsRepository {

    private final EntityManager em;

    public JpaTransactionsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<Transactions> findTransactions(String accountNumber, String startDate, String endDate, String transactionType, String sortOrder, Pageable pageable) {
        String queryString = "SELECT t FROM Transactions t WHERE t.accountNumber = :accountNumber " +
                             "AND t.transactionDate BETWEEN :startDate AND :endDate " +
                             ("".equals(transactionType) ? "AND t.transactionType IS NOT NULL" : "AND t.transactionType = :transactionType") +
                             " ORDER BY t.transactionType " + sortOrder;

        TypedQuery<Transactions> query = em.createQuery(queryString, Transactions.class)
                .setParameter("accountNumber", accountNumber)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        if(!"".equals(transactionType)) query.setParameter("transactionType", transactionType);

        // 페이징 처리
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // 전체 결과 수를 가져오기 위한 쿼리
        String countQueryString = "SELECT COUNT(t) FROM Transactions t WHERE t.accountNumber = :accountNumber " +
                                  "AND t.transactionDate BETWEEN :startDate AND :endDate " +
                                  ("".equals(transactionType) ? "AND t.transactionType IS NOT NULL" : "AND t.transactionType = :transactionType");

        TypedQuery<Long> countQuery = em.createQuery(countQueryString, Long.class)
                .setParameter("accountNumber", accountNumber)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        if (!"".equals(transactionType)) countQuery.setParameter("transactionType", transactionType);

        long total = countQuery.getSingleResult();

        System.out.println("ArraySize : " + total);

        return new PageImpl<>(query.getResultList(), pageable, total);
    }

    @Override
    public Transactions save(Transactions transactions) {
        em.persist(transactions);
        return transactions;
    }
}
