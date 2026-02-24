package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SvcBgnAppointDAO.java
 * @Description : 관리자 WEB에서 사용하는 건강정보관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.08		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.SvcBgnAppointDAO")
public class SvcBgnAppointDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String,String>> selectServiceBeginApList(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhc.web.sv.svcbgnappoint.selectServiceBeginApList", param);		
		return rsList;  
	}
	
	
	public Map<String, Object> selectServiceBeginApDtls(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.sv.svcbgnappoint.selectServiceBeginApDtls", param);
		return rsMap;
	}
	
	
	public List<Map<String, Object>> selectServiceSchedule(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.svcbgnappoint.selectServiceSchedule", param);
		return rsList;
	}
	
	public void updateReExamSttus(Map<String, Object> param) throws Exception{
		//1.서비스 참여 테이블 히스토리 저장
		insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtHist", param);
		//2.서비스 참여 테이블 대상자 상태 정보 업데이트
		update("mhc.web.tg.svcjoinmngt.updateSvcJoinMngtTrgterSttus", param);
		//3.예비대상자 테이블 예비대상자 상태 정보 업데이트
		update("mhc.web.tg.svcjoinmngt.updatePreTrgterSttus", param);
	}
	
}
