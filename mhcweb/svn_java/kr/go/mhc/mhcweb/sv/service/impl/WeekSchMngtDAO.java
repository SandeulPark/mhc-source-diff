package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : WeekSchMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 주차별 일정을 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.04.10		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.04.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.WeekSchMngtDAO")
public class WeekSchMngtDAO extends DMultiEgovAbstractMapper{
	
	/**
	 * 주차별 일정 관리화면 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectWeekSchMngtList(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhc.web.sv.weekschmngt.selectWeekSchMngtList", param);		
		return rsList;  
	}
	
	/**
	 * 주차별 일정 관리 사용자 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectWeekSchMngtUserList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.sv.weekschmngt.selectWeekSchMngtUserList", param);
		return rsList;
	}
	
	
	/**
	 * 주차별 일정 관리 변경 전 주차 정보
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> selectWeekSchMngtBeforeWeekInfo(Map<String, Object> param) throws Exception{
		Map<String,String> rsMap = selectOne("mhc.web.sv.weekschmngt.selectWeekSchMngtBeforeWeekInfo", param);
		return rsMap;
	}	
	
	/**
	 * 주차별 일정 관리 변경 후 주차 목록
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectWeekSchMngtAfterWeekList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.sv.weekschmngt.selectWeekSchMngtAfterWeekList", param);
		return rsList;
	}	
	
	
	
	
	/**
	 * 주차별 일정 관리 사용자 일정 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int saveWeekSchMngt(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		
		String[] weekUserInfo = param.get("modalwrapInfo").toString().split(",");	
		int weekUserCnt = Integer.parseInt(param.get("modalwrapCnt").toString());

		try{
			int j=0;
			Map<String, Object> infoMap = new HashMap<String, Object>();
			for(int i=0; i < weekUserCnt; i++){
				infoMap.clear();
				infoMap.put("USER_ID", 		  weekUserInfo[i+j]);
				infoMap.put("CNSL_SN", 		  weekUserInfo[i+j+1]);
				infoMap.put("BEFORE_WEEK_CNT",  weekUserInfo[i+j+2]);
				infoMap.put("AFTER_WEEK_CNT",   weekUserInfo[i+j+3]);		
				infoMap.put("SVC_MNGT_NO", weekUserInfo[i+j+4]);
				rsInt += update("mhc.web.sv.weekschmngt.saveWeekSchMngt", infoMap);

				j+=4;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rsInt; 
	}
	
}
