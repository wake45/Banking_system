package Project._Percent_Project.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transactions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "transaction_id")
    private Long transactionId;        //거래 ID

    @Column(name = "account_number")
    private String accountNumber;     //계좌번호 ID

    @Column(name = "transaction_amount")
    private int transactionAmount;    //거래 금액

    @Column(name = "transaction_type")
    private String transactionType;   //거래 유형

    @Column(name = "deposit_id")
    private String depositId;         //입금 ID

    @Column(name = "withdraw_id")
    private String withdrawId;        //출금 ID

    @Column(name = "memo")
    private String memo;              //적요

    @Column(name = "transaction_date")
    private String transactionDate;     //거래 일자

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(String withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
