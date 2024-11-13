package Project._Percent_Project.repository;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> findById(String userId);                   //보유 계좌 조회
    Optional<String> findId(String accountNumber);           //계좌 소유주 & 유효성 확인
    Optional<String> findPassword(String accountNumber);     //계좌 비밀번호 확인
    Optional<Integer> findBalance(String accountNumber);     //계좌 잔액 확인
    Transactions updateAccount(Transactions transactions);   //이체 정보 저장
}
