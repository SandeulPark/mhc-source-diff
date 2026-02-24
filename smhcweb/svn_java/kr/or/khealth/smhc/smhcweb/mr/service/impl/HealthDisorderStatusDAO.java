package kr.or.khealth.smhc.smhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;









import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : HealthDisorderInfoDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 건강 이상 정보 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2020.09.16		양현우		수정
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.HealthDisorderStatusDAO")
public class HealthDisorderStatusDAO extends DMultiEgovAbstractMapper {

	public List<Map<String, Object>> selectHealthDisorderStatusList(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhc.web.mr.healthdisorderstatus.selectHealthDisorderStatusList",param);
		return rsList;
	}
	
	//대상자정보관리 혈압 및 혈당 이상 측정정보 처리내역저장 팝업창 update 업데이트 저장 
	public void updateDisorderExamProc(Map<String, Object> param) throws Exception {
		update("smhc.web.mr.healthdisorderstatus.updateDisorderExamProc", param);
	}

	public List<Map<String, Object>> selectDisorderStatusCount(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhc.web.mr.healthdisorderstatus.selectDisorderStatusCount",param);
		return rsList;
	}
	
}
