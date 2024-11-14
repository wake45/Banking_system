package Project._Percent_Project.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaDataInsertRepository implements DataInsertRepository{

    private final EntityManager em;

    public JpaDataInsertRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void DataInsert() {
        String queryString = "INSERT INTO Users(user_id, user_name, create_date, modify_date) VALUES " +
                "('001', '에잇퍼센트', '20241110', '20241110'), " +
                "('002', '카카오뱅크', '20241110', '20241110'), " +
                "('003', '토스뱅크', '20241110', '20241110');";

        em.createNativeQuery(queryString).executeUpdate();

        queryString = "INSERT INTO Account(account_number, user_id, account_balance, account_password, account_type, create_date, modify_date) VALUES " +
                "('10011001', '001', 999999, '1004', '01', '20241112', '20241112'), " +
                "('10011002', '002', 0, '1004', '01', '20241112', '20241112'), " +
                "('10011003', '003', 10000, '1004', '01', '20241112', '20241112');";

        em.createNativeQuery(queryString).executeUpdate();
    }
}
