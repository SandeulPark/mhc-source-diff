package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : HealthDisValMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 건강이상수치 설정 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.02.17		나연이			최초생성
 *
 * @author theJoin
 * @since 2017.02.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sm.HealthDisValMngtServiceDAO")
public class HealthDisValMngtServiceDAO extends DMultiEgovAbstractMapper{
	
	/**
	 * 기관별 건강이상수치 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getHealthDisValList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.sm.healthdisvalmngt.selectHealthDisValList", param);	
		return rsList;  
	}
	
	
	/**
	 * 기관명 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getOrgCdList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.healthdisvalmngt.selectOrgCdList", param);
		return rsList;
	}
	
	/**
	 * 기관별 건강이상수치 정보 신규등록 및 수정
	 * @param param
	 * @throws Exception
	 */
	public int mergeHealthDisVal(Map<String, Object> param) throws Exception{
		return insert("mhc.web.sm.healthdisvalmngt.mergeHealthDisVal", param);
	}
	
}
