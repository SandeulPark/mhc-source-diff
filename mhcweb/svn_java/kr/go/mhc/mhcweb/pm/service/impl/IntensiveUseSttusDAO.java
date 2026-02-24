package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : IntensiveUseSttusDAO.java
 * @Description : 관리자 WEB에서 사용하는 집중상담 이용현황 실적관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		유준영			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.pm.IntensiveUseSttusDAO")
public class IntensiveUseSttusDAO extends EgovAbstractMapper {

	//서비스 참여 정보 목록 조회
	public List<Map<String, Object>> selectIntensiveUseSttusList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.intensiveusesttus.selectIntensiveUseSttusList", param);
		return rsList;
	}
	
	//서비스 참여 정보 목록 조회
	public List<Map<String, Object>> selectIntensiveUseSttusTrgterList(Map<String, Object> param) throws Exception {		
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.intensiveusesttus.selectIntensiveUseSttusTrgterList", param);		
		return rsList;
	}

	public List<Map<String, Object>> selectIntensiveUseSttusListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.intensiveusesttus.selectIntensiveUseSttusListNew", param);
		return rsList;
	}

	public List<Map<String, Object>> selectIntensiveUseSttusTrgterListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.intensiveusesttus.selectIntensiveUseSttusTrgterListNew", param);
		return rsList;
	}	
	
}
