package Project._Percent_Project.controller;

public class TransferForm {
    private String accountNumber;
    private String accountPassword;
    private int accountBalance ;
    private String depositAccountNumber;
    private String memo;
    private String id;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getDepositAccountNumber() {
        return depositAccountNumber;
    }

    public void setDepositAccountNumber(String depositAccountNumber) {
        this.depositAccountNumber = depositAccountNumber;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
