package Project._Percent_Project.controller;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.domain.Transactions;
import Project._Percent_Project.service.AccountService;
import Project._Percent_Project.service.TransactionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/transactionsViewMenu")
    public String TransactionsView(@RequestParam("id") String id, Model model){
        model.addAttribute("id",id);

        // 모델에 계좌 목록 추가
        List<Account> accountList = accountService.findById(id);
        model.addAttribute("accountList", accountList);

        return "TransactionsHistoryInquiryView"; // 거래내역 조회 페이지로 이동
    }

    @PostMapping("/transactionsHistoryInquiry")
    public String TransactionsHistoryInquiry(TransactionsHistoryInquiryForm transactionsHistoryInquiryForm, Model model, @RequestParam("page") int page, @PageableDefault(size = 5, sort = "transactionDate") Pageable pageable){

        //조회 전 계좌 소유주 조회
        Transactions transaction = new Transactions();
        transaction.setAccountNumber(transactionsHistoryInquiryForm.getAccountNumber());
        accountService.validateDepositAccount(transaction); //validateAccountOwner

        Sort sort = transactionsHistoryInquiryForm.getInquirySort().equalsIgnoreCase("asc") ?
                Sort.by("transactionDate").ascending() :
                Sort.by("transactionDate").descending();
        pageable = PageRequest.of(page, pageable.getPageSize(), sort);

        Page<Transactions> transactionList = transactionsService.findTransactions(transactionsHistoryInquiryForm.getAccountNumber(), transactionsHistoryInquiryForm.getInquiryStartDate(), transactionsHistoryInquiryForm.getInquiryEndDate(), transactionsHistoryInquiryForm.getInquiryType(), transactionsHistoryInquiryForm.getInquirySort(), pageable);

        int nowPage = transactionList.getPageable().getPageNumber(); // 현재 페이지 (0부터 시작)
        int startPage = Math.max(nowPage - 2, 0); // 시작 페이지 (현재 페이지의 2개 이전 페이지)
        int endPage = Math.min(nowPage + 2, transactionList.getTotalPages() - 1); // 끝 페이지 (현재 페이지의 2개 이후 페이지)

        if(startPage > endPage) endPage = startPage;

        model.addAttribute("transactionsHistoryInquiryForm",transactionsHistoryInquiryForm);
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "TransactionsHistoryInquiryResultsView"; // 거래내역 조회 페이지로 이동
    }
}
