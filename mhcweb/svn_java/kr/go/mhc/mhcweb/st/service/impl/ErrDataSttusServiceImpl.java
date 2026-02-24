package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import kr.go.mhc.mhcweb.st.service.ErrDataSttusService;

@Service(value= "web.st.ErrDataSttusService")
public class ErrDataSttusServiceImpl implements ErrDataSttusService {

    @Resource(name="web.st.ErrDataSttusDAO")
    private ErrDataSttusDAO errDataSttusDAO;

    // 에러 데이터 현황 연동 건수별 리스트 조회
    @Override
    public List<Map<String, String>> errDataSyncList(Map<String, Object> param) throws Exception {
        return errDataSttusDAO.errDataSyncList(param);
    }

    // 에러 데이터 현황 앱 버전별 리스트 조회
    @Override
    public List<Map<String, String>> errDataAppVerList(Map<String, Object> param) throws Exception {
        return errDataSttusDAO.errDataAppVerList(param);
    }

    // 에러 데이터 현황 업체별 리스트 조회
    @Override
    public List<Map<String, String>> errDataModelList(Map<String, Object> param) throws Exception {
        return errDataSttusDAO.errDataModelList(param);
    }

    // 에러 데이터 현황 오류 코드별 리스트 조회
    @Override
    public List<Map<String, String>> errDataCodeList(Map<String, Object> param) throws Exception {
        return errDataSttusDAO.errDataCodeList(param);
    }
}
