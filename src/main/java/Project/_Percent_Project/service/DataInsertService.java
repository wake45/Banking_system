package Project._Percent_Project.service;

import Project._Percent_Project.repository.DataInsertRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataInsertService {
    private final DataInsertRepository dataInsertRepository;

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
