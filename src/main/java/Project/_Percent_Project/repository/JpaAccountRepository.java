package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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
    public Optional<Integer> findBalance(String accountNumber) {
        return em.createQuery("select a.accountBalance from Account a where a.accountNumber = :accountNumber", Integer.class)
                .setParameter("accountNumber", accountNumber)
                .getResultStream()
                .findFirst(); // 결과가 없을 경우 Optional.empty() 반환
    }

    @Override
    public Optional<Integer> updateAccount(Transactions transactions) {

        if("01".equals(transactions.getTransactionType())){ //출금
            em.createQuery("UPDATE Account a SET a.accountBalance = a.accountBalance - :accountBalance , a.modifyDate = :modifyDate WHERE a.accountNumber = :accountNumber ")
                    .setParameter("accountBalance", transactions.getTransactionAmount())
                    .setParameter("modifyDate", transactions.getTransactionDate())
                    .setParameter("accountNumber", transactions.getAccountNumber())
                    .executeUpdate();
        }else if("02".equals(transactions.getTransactionType())){ //입금
            em.createQuery("UPDATE Account a SET a.accountBalance = a.accountBalance + :accountBalance , a.modifyDate = :modifyDate WHERE a.accountNumber = :accountNumber ")
                    .setParameter("accountBalance", transactions.getTransactionAmount())
                    .setParameter("modifyDate", transactions.getTransactionDate())
                    .setParameter("accountNumber", transactions.getAccountNumber())
                    .executeUpdate();
        }

        return em.createQuery("select a.accountBalance from Account a where a.accountNumber = :accountNumber", Integer.class)
                .setParameter("accountNumber", transactions.getAccountNumber())
                .getResultStream()
                .findFirst();
    }
}
