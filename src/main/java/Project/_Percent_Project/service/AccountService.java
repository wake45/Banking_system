package Project._Percent_Project.service;

import Project._Percent_Project.domain.Transactions;
import Project._Percent_Project.repository.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 출금
     */
    public Long withdraw(Transactions transactions, String password){  //출금
        validateAccountOwner(transactions); //계좌 소유주 확인
        validateAccountPassword(transactions, password); //계좌 비밀번호 확인
        validateAccountBalance(transactions); //계좌 잔액 확인

        accountRepository.updateAccount(transactions); //출금 거래내역 적재
        return transactions.getTransactionId();
    }
    
    /**
     * 입금
     */
    public Long deposit (Transactions transactions){ //입금
        validateDepositAccount(transactions); //입금계좌 유효성 확인

        accountRepository.updateAccount(transactions); //입금 거래내역 적재
        return transactions.getTransactionId();
    }


    private void validateAccountOwner(Transactions transactions) {
        Optional<String> optionalUserId = accountRepository.findId(transactions.getAccountNumber());
        //계좌 소유주 확인
        optionalUserId.filter(ownerCheck -> ownerCheck.equals(transactions.getWithdrawId()))
                .orElseThrow(() -> new IllegalStateException("본인 출금계좌만 이체 가능합니다."));
    }

    private void validateAccountPassword(Transactions transactions, String password) {
        Optional<String> optionalPassword = accountRepository.findPassword(transactions.getAccountNumber());
        //계좌 비밀번호 확인
        optionalPassword.filter(passwordCheck -> passwordCheck.equals(password))
                .orElseThrow(() -> new IllegalStateException("출금계좌 비밀번호가 일치하지 않습니다."));
    }

    private void validateAccountBalance(Transactions transactions) {
        Optional<Integer> optionalBalance = accountRepository.findBalance(transactions.getAccountNumber());
        //계좌 잔액 확인
        optionalBalance.filter(balanceCheck -> balanceCheck < transactions.getTransactionAmount())
                .orElseThrow(() -> new IllegalStateException("출금계좌 잔액이 부족합니다."));
    }

    private void validateDepositAccount(Transactions transactions) {
        Optional<String> optionalUserId = accountRepository.findId(transactions.getAccountNumber());
        //입금계좌 유효성 확인
        optionalUserId.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입금계좌 번호입니다."));
    }
}
