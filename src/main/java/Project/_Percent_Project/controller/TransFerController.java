package Project._Percent_Project.controller;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import Project._Percent_Project.service.AccountService;
import Project._Percent_Project.service.TransactionsService;
import Project._Percent_Project.service.TransferException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class TransFerController {
    private final AccountService accountService;
    private final TransactionsService transactionsService;

    public TransFerController(AccountService accountService, TransactionsService transactionsService) {
        this.accountService = accountService;
        this.transactionsService = transactionsService;
    }

    @PostMapping("/transferMenu")
    public String TransferView(@RequestParam("id") String id, Model model){
        model.addAttribute("id",id);

        // 모델에 계좌 목록 추가
        List<Account> accountList = accountService.findById(id);
        model.addAttribute("accountList", accountList);

        return "TransferView"; // 이체 페이지로 이동
    }

    @PostMapping("/transfer")
    @ExceptionHandler(TransferException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 상태 코드
    public String Transfer(TransferForm transferForm, Model model) {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = today.format(formatter);

        //이체 관련 비즈니스 로직
        Transactions withdrawTransaction = getWithdrawTransaction(transferForm, formattedDate);
        Transactions depositTransaction = getDepositTransaction(transferForm, formattedDate);
        Optional<String> depositId = accountService.validateDepositAccount(depositTransaction); //이체 전 입금계좌 수취조회
        withdrawTransaction.setDepositId(depositId.orElse(""));
        depositTransaction.setDepositId(depositId.orElse(""));
        Optional<AccountBalancesForm> AfterBalance;
        try {
            AfterBalance = accountService.transfer(withdrawTransaction, depositTransaction, transferForm.getAccountPassword());
        } catch (TransferException e) {
            System.out.println("TransferException : " + e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; // 오류 시 보여줄 뷰
        }

        //거래내역 저장(출금,입금)
        if (AfterBalance.isPresent()) {
            AccountBalancesForm balances = AfterBalance.get();
            withdrawTransaction.setAccountBalance(balances.getWithdrawBalance());
            depositTransaction.setAccountBalance(balances.getDepositBalance());
        }
        transactionsService.saveTransaction(withdrawTransaction);
        transactionsService.saveTransaction(depositTransaction);

        model.addAttribute("accountNumber", transferForm.getAccountNumber());
        model.addAttribute("accountBalance", transferForm.getAccountBalance());
        model.addAttribute("accountAfterBalance", AfterBalance.map(AccountBalancesForm::getWithdrawBalance).orElse(0));
        model.addAttribute("depositAccountNumber", transferForm.getDepositAccountNumber());
        model.addAttribute("depositId", depositId.orElse(""));
        model.addAttribute("memo", transferForm.getMemo());

        return "TransferResultView";
    }

    private static Transactions getWithdrawTransaction(TransferForm transferForm, String formattedDate) {
        Transactions withdrawTransaction = new Transactions();

        withdrawTransaction.setAccountNumber(transferForm.getAccountNumber());
        withdrawTransaction.setTransactionAmount(transferForm.getAccountBalance());
        withdrawTransaction.setTransactionType("01"); //출금
        withdrawTransaction.setWithdrawId(transferForm.getId());
        withdrawTransaction.setMemo(transferForm.getMemo());
        withdrawTransaction.setTransactionDate(formattedDate);

        return withdrawTransaction;
    }

    private static Transactions getDepositTransaction(TransferForm transferForm, String formattedDate) {
        Transactions depositTransaction = new Transactions();

        depositTransaction.setAccountNumber(transferForm.getDepositAccountNumber());
        depositTransaction.setTransactionAmount(transferForm.getAccountBalance());
        depositTransaction.setTransactionType("02"); //입금
        depositTransaction.setWithdrawId(transferForm.getId());
        depositTransaction.setTransactionDate(formattedDate);

        return depositTransaction;
    }

}
