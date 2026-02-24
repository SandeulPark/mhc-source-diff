package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
  * @Class Name : MealDiaryRegDAO.java
 * @Description : 관리자 WEB에서 사용하는 식단등록 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.05.11		   			 최초생성
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.gn.GnrlMealDiaryRegDAO")
public class GnrlMealDiaryRegDAO extends EgovAbstractMapper {
	
	//식단등록 목록 조회
	public List<Map<String, Object>> getMealDiaryRegList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlmealdiaryreg.selectMealDiaryRegList", param);
		return rsList;
	}
	

	//식사일기 등록정보 엑셀다운로드 조회 (그리드)
	public List<Map<String, Object>> getMealDiaryExcel(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlmealdiaryreg.selectMealDiaryExcelList", param);
		return rsList;
	}
}
