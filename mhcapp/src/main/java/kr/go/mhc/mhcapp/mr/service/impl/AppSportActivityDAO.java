package kr.go.mhc.mhcapp.mr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : AppSportActivityDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-스포츠활동인증 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * 		2021.11.25		윤찬호				최초생성
 *
 * @author chyoon
 * @since 2021.11.25
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.mr.AppSportActivityDAO")
public class AppSportActivityDAO extends DMultiEgovAbstractMapper{

	public int insertSportActivityUserInfo(Map<String, Object> param) {
		return insert("mhcapp.mr.sportactivity.insertSportActivityUserInfo", param);
	}

	public Map<String, Object> chkExistSportActivityUserInfo(Map<String, Object> param) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int userCnt = selectOne("mhcapp.mr.sportactivity.chkExistSportActivityUserCnt", param);
		if(userCnt > 0) {
			rsMap = selectOne("mhcapp.mr.sportactivity.chkExistSportActivityUserInfo",param);
		}else {
			rsMap.put("USER_CNT", 0);
		}
		return rsMap;
	}

	public void updateSportActivityUserAgree(Map<String, Object> param) {
		update("mhcapp.mr.sportactivity.updateSportActivityUserAgree", param);		
	}

	public void updateSportActivityCertWrite(Map<String, Object> param) {
		update("mhcapp.mr.sportactivity.updateSportActivityCertWrite", param);	
		
	}

	public void delUserInfo(Map<String, Object> param) {
		delete("mhcapp.mr.sportactivity.delUserInfo", param);			
		
	}

	public Map<String, Object> getSportActivityWalkCnt(Map<String, Object> param) {	
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = selectOne("mhcapp.mr.sportactivity.getSportActivityWalkCnt",param);
		
		return rsMap;
	}

	public Map<String, Object> getSportActivityMeasureYmd(Map<String, Object> param) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = selectOne("mhcapp.mr.sportactivity.getSportActivityMeasureYmd",param);
		
		return rsMap;
	}

	public Map<String, Object> userInfo(String param) {	
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = selectOne("mhcapp.mr.sportactivity.getUserInfo",param);
		
		return rsMap;
	}
	
	public List<Map<String, Object>> chkGnGroupActivityUserInfo(String param) {	
		List<Map<String, Object>> rsMap = new ArrayList<Map<String,Object>>();
		rsMap =  selectList("mhcapp.mr.sportactivity.chkGnGroupActivityUserInfo",param);
		System.out.println("zzzzzzzzz"+rsMap);
		return rsMap;
	}
	
	
}
