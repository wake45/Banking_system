package Project._Percent_Project.service;

import Project._Percent_Project.controller.AccountBalancesForm;
import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import Project._Percent_Project.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 보유계좌 조회
     */
    public List<Account> findById(String userId){
        return accountRepository.findById(userId);
    }

    /**
     * 이체
     */
    public Optional<AccountBalancesForm> transfer(Transactions withdrawTransaction, Transactions depositTransaction, String accountPassword) {
        Optional<Integer> withdrawAfterBalance = withdraw(withdrawTransaction, accountPassword);
        Optional<Integer> depositAfterBalance = deposit(depositTransaction);

        AccountBalancesForm balances = null;
        if (withdrawAfterBalance.isPresent() && depositAfterBalance.isPresent()) {
            balances = new AccountBalancesForm(withdrawAfterBalance.get(),depositAfterBalance.get());
        }

        return Optional.ofNullable(balances);
    }

    /**
     * 출금
     */
    public Optional<Integer> withdraw(Transactions transactions, String password){  //출금
        try {
            validateAccountOwner(transactions); //계좌 소유주 확인
            validateAccountPassword(transactions, password); //계좌 비밀번호 확인
            validateAccountBalance(transactions); //계좌 잔액 확인

            return accountRepository.updateAccount(transactions); //출금 거래내역 적재
        }catch (RuntimeException e){
            throw new RuntimeException("출금 처리 중 오류가 발생했습니다.", e);
        }
    }
    
    /**
     * 입금
     */
    public Optional<Integer> deposit (Transactions transactions){ //입금
        try {
            //validateDepositAccount(transactions); //이체 전 수취조회를 위해 로직 분리

            return accountRepository.updateAccount(transactions); //입금 거래내역 적재
        }catch (RuntimeException e){
            throw new RuntimeException("입금 처리 중 오류가 발생했습니다.", e);
        }
    }

    private void validateAccountOwner(Transactions transactions) {
        Optional<String> optionalUserId = accountRepository.findId(transactions.getAccountNumber());
        //계좌 소유주 확인
        optionalUserId.filter(ownerCheck -> ownerCheck.equals(transactions.getWithdrawId()))
                .orElseThrow(() -> new TransferException("본인 출금계좌만 이체 가능합니다."));
    }

    private void validateAccountPassword(Transactions transactions, String password) {
        Optional<String> optionalPassword = accountRepository.findPassword(transactions.getAccountNumber());
        //계좌 비밀번호 확인
        optionalPassword.filter(passwordCheck -> passwordCheck.equals(password))
                .orElseThrow(() -> new TransferException("출금계좌 비밀번호가 일치하지 않습니다."));
    }

    private void validateAccountBalance(Transactions transactions) {
        Optional<Integer> optionalBalance = accountRepository.findBalance(transactions.getAccountNumber());
        //계좌 잔액 확인
        optionalBalance.filter(balanceCheck -> balanceCheck >= transactions.getTransactionAmount())
                .orElseThrow(() -> new TransferException("출금계좌 잔액이 부족합니다."));
    }

    public Optional<String> validateDepositAccount(Transactions transactions) {
        Optional<String> optionalUserId = accountRepository.findId(transactions.getAccountNumber());
        //입금계좌 유효성 확인
        optionalUserId.orElseThrow(() -> new TransferException("유효하지 않은 입금계좌 번호입니다."));
        return optionalUserId;
    }

}
