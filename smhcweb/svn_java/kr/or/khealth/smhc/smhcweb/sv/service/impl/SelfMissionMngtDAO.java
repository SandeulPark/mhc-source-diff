package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


/**
 * @Class Name : SelfMissionMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 미션설정 관리업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.11.09		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.11.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.SelfMissionMngtDAO")
public class SelfMissionMngtDAO  extends DMultiEgovAbstractMapper{
	
	public List<Map<String, Object>> selectSelfMissionMngtList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.selfmissionmngt.selectSelfMissionMngtsList", param);
		return rsList;
	}
	
	public int insertSelfMissionMngt(Map<String, Object> param) throws Exception{
		int rsInt =0;
		rsInt = insert("smhc.web.sv.selfmissionmngt.insertSelfMissionMngt",param);
		return rsInt;
	}
	
	public int updSelfMissionMngt(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt = update("smhc.web.sv.selfmissionmngt.updSelfMissionMngt", param);
		return rsInt;
	}
	
	public Map<String, Object> selectSelfMissionMngtCount(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.sv.selfmissionmngt.selectSelfMissionMngtCount", param);
		return rsMap;
	}

}
