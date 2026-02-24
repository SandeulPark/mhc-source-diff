package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : SportActivityDAO.java
 * @Description : 관리자 WEB에서 사용하는 스포츠 활동 인증 현황 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.12.09		chyoon			최초생성
 *
 * @author chyoon
 * @since 2021.12.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.gn.SportActivityDAO")
public class GnrlSportActivityDAO extends EgovAbstractMapper{

	public List<Map<String, Object>> getSportActivityUserList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlsportactivity.getSportActivityUserList", param);
		return rsList;
	}

	public Map<String, Object> getSportActivityCertMsg(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String, Object> rsMap = selectOne("mhc.web.gn.gnrlsportactivity.selectSportActivityCertMsg", param);
		return rsMap;
	}
	
	public Map<String, Object> userInfo(String param) {	
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = selectOne("mhc.web.gn.gnrlsportactivity.getUserInfo",param);
		
		return rsMap;
	}	

}
