package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaAccountRepository implements AccountRepository {

    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Account> findById(String userId) {
        return em.createQuery("select a from Account a where a.userId = :userId", Account.class)
                .setParameter("userId",userId).getResultList();
    }

    @Override
    public int findAccountNumber(String accountNumber) {
        return ((Number) em.createQuery("select count(a) from Account a where a.accountNumber = :accountNumber")
                .setParameter("accountNumber", accountNumber)
                .getSingleResult()).intValue();
    }

    @Override
    public Optional<String> findId(String accountNumber) {
        return em.createQuery("select a.userId from Account a where a.accountNumber = :accountNumber", String.class)
                .setParameter("accountNumber", accountNumber)
                .getResultStream()
                .findFirst(); // 결과가 없을 경우 Optional.empty() 반환
    }

    @Override
    public Optional<String> findPassword(String accountNumber) {
        return em.createQuery("select a.accountPassword from Account a where a.accountNumber = :accountNumber", String.class)
                .setParameter("accountNumber", accountNumber)
                .getResultStream()
                .findFirst(); // 결과가 없을 경우 Optional.empty() 반환
    }

    @Override
    public Optional<String> findBalance(String accountNumber) {
        return em.createQuery("select a.accountBalance from Account a where a.accountNumber = :accountNumber", String.class)
                .setParameter("accountNumber", accountNumber)
                .getResultStream()
                .findFirst(); // 결과가 없을 경우 Optional.empty() 반환
    }

    @Override
    public Transactions saveTransaction(Transactions transactions) {
        em.persist(transactions);
        return transactions;
    }
}
