package Project._Percent_Project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {

    @Id @Column(name = "user_id")
    private String userId;  //사용자 ID

    @Column(name = "user_name")
    private String userName; //이름

    @Column(name = "create_date")
    private int createDate;    //생성일

    @Column(name = "modify_date")
    private String modifyDate; //수정일

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
