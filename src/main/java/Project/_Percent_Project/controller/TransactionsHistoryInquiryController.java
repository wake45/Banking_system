package Project._Percent_Project.controller;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import Project._Percent_Project.service.AccountService;
import Project._Percent_Project.service.TransactionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TransactionsHistoryInquiryController {
    private final AccountService accountService;
    private final TransactionsService transactionsService;

    public TransactionsHistoryInquiryController(AccountService accountService, TransactionsService transactionsService) {
        this.accountService = accountService;
        this.transactionsService = transactionsService;
    }

    @PostMapping("/transactionsViewMenu")
    public String TransferView(@RequestParam("action") String action, @RequestParam("id") String id, Model model){
        model.addAttribute("id",id);

        // 모델에 계좌 목록 추가
        List<Account> accountList = accountService.findById(id);
        model.addAttribute("accountList", accountList);

        return "TransactionsHistoryInquiryView"; // 거래내역 조회 페이지로 이동
    }

    @PostMapping("/transactionsHistoryInquiry")
    public String TransactionsHistoryInquiry(TransactionsHistoryInquiryForm transactionsHistoryInquiryForm, Model model){

        //조회 전 계좌 소유주 조회
        Transactions transaction = new Transactions();
        transaction.setAccountNumber(transactionsHistoryInquiryForm.getAccountNumber());
        accountService.validateDepositAccount(transaction);

        List<Transactions> transactionList = transactionsService.findTransactions(transactionsHistoryInquiryForm.getAccountNumber(), transactionsHistoryInquiryForm.getInquiryStartDate(), transactionsHistoryInquiryForm.getInquiryEndDate(), transactionsHistoryInquiryForm.getInquiryType(), transactionsHistoryInquiryForm.getInquirySort());
        model.addAttribute("transactionList", transactionList);


        return "TransactionsHistoryInquiryResultsView"; // 거래내역 조회 페이지로 이동
    }
}
