package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : AppServiceUseSttusDAO.java
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

@Repository("web.pm.AppServiceUseSttusDAO")
public class AppServiceUseSttusDAO extends EgovAbstractMapper {

	//APP서비스 이용현황 목록 조회
	public List<Map<String, Object>> selectAppServiceUseSttusList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.appserviceusesttus.selectAppServiceUseSttusList", param);
		return rsList;
	}
	
	//APP서비스 이용현황 대상자 목록 조회
	public List<Map<String, Object>> selectAppServiceUseSttusTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.appserviceusesttus.selectAppServiceUseSttusTrgterList", param);
		return rsList;
	}
	
	//APP서비스 이용현황 목록 조회(실적개편)
	public List<Map<String, Object>> selectAppServiceUseSttusListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.appserviceusesttus.selectAppServiceUseSttusListNew", param);
		return rsList;
	}
	
	//APP서비스 이용현황 대상자 목록 조회(실적개편)
	public List<Map<String, Object>> selectAppServiceUseSttusTrgterListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.appserviceusesttus.selectAppServiceUseSttusTrgterListNew", param);
		return rsList;
	}	
}
