package Project._Percent_Project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Account {
    @Id @Column(name = "account_number")
    private String accountNumber;  //계좌번호
    @Column(name = "user_id")
    private String userId;         //사용자 ID
    @Column(name = "account_balance")
    private int accountBalance;    //계좌 잔액
    @Column(name = "account_password")
    private String accountPassword;//계좌 비밀번호
    @Column(name = "account_type")
    private String accountType;    //계좌 유형(01:입출금,02:예금,03:적금)
    @Column(name = "create_date")
    private Date createDate;       //생성일
    @Column(name = "modify_date")
    private Date modifyDate;       //수정일

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
