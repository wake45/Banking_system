package Project._Percent_Project.controller;

public class AccountBalancesForm {
    private Integer withdrawBalance;
    private Integer depositBalance;

    public AccountBalancesForm(Integer withdrawBalance, Integer depositBalance) {
        this.withdrawBalance = withdrawBalance;
        this.depositBalance = depositBalance;
    }

    public Integer getWithdrawBalance() {
        return withdrawBalance;
    }

    public Integer getDepositBalance() {
        return depositBalance;
    }
}
