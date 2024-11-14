package Project._Percent_Project.service;

import Project._Percent_Project.domain.Account;
import Project._Percent_Project.repository.AccountRepository;
import Project._Percent_Project.repository.DataInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DataInsertService {
    private final DataInsertRepository dataInsertRepository;

    @Autowired
    public DataInsertService(DataInsertRepository dataInsertRepository) {
        this.dataInsertRepository = dataInsertRepository;
    }

    /**
     * 임시 데이터 저장
     */
    public void DataInsert(){
        dataInsertRepository.DataInsert();
    }
}
