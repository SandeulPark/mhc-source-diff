package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
  * @Class Name : MealRegDAO.java
 * @Description : 관리자 WEB에서 사용하는 식단등록 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.12.05		이은주			최초생성
 *
 * @author gst
 * @since 2016.12.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.MealRegDAO")
public class MealRegDAO extends EgovAbstractMapper {
	
	//식단등록 목록 조회
	public List<Map<String, Object>> mealRegList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.mealreg.mealRegList", param);
		return rsList;
	}
	
	//식단등록 상세 조회
	public Map<String, Object> mealRegPopDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.mr.mealreg.mealRegPopDtls", param);
		return rsMap;
	}
	
	//식단등록 상제 조회 2
	public List<Map<String, Object>> mealRegPopDtls2(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.mealreg.mealRegPopDtls2", param);
		return rsList;
	}
}
