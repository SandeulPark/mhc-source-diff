package kr.or.khealth.smhc.smhcweb.sv.service.impl;


import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : ErrDataSttusDAO.java
 * @Description : 에러 데이터 현황 정보를 조회하는 Class
 * @Modification Information
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.ErrDataSttusDAO")
public class ErrDataSttusDAO extends DMultiEgovAbstractMapper {

    /**
     * 에러 데이터 현황 연동 건수별 리스트 조회
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> errDataSyncList(Map<String, Object> param) throws Exception {
        List<Map<String, String>> rsList = selectList("smhc.web.sv.errdatasttus.errDataSyncList", param);
        return rsList;
    }

    /**
     * 에러 데이터 현황 앱 버전별 리스트 조회
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> errDataAppVerList(Map<String, Object> param) throws Exception {
        List<Map<String, String>> rsList = selectList("smhc.web.sv.errdatasttus.errDataAppVerList", param);
        return rsList;
    }

    /**
     * 에러 데이터 현황 업체별 리스트 조회
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> errDataModelList(Map<String, Object> param) throws Exception {
        List<Map<String, String>> rsList = selectList("smhc.web.sv.errdatasttus.errDataModelList", param);
        return rsList;
    }

    /**
     * 에러 데이터 현황 오류 코드별 리스트 조회
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> errDataCodeList(Map<String, Object> param) throws Exception {
        List<Map<String, String>> rsList = selectList("smhc.web.sv.errdatasttus.errDataCodeList", param);
        return rsList;
    }
}