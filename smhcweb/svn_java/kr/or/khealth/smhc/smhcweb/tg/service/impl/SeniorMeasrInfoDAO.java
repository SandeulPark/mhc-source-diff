package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : SeniorMeasrInfoDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 측정현황 조회 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.15		오샘이			수정
 *
 * @author thejoin
 * @since 2020.10.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.SeniorMeasrInfoDAO")
public class SeniorMeasrInfoDAO extends EgovAbstractMapper{
	
	//대상자 총괄현황 최근7일간 측정 횟수 조회
	public Map<String, Object> selectMeasrWeekCntInfo(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.tg.seniormeasrinfo.selectMeasrWeekCntInfo", param);
		return rsMap;
	}
	
	//대상자 총괄현황 미측정 경과일 조회
	public Map<String, Object> selectMeasrNoMeasrInfo(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.tg.seniormeasrinfo.selectMeasrNoMeasrInfo", param);
		return rsMap;
	}	
	
	//대상자총괄관리 활동 목록 조회
	public List<Map<String, Object>> selectActChartList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormeasrinfo.selectActChartList", param);
		return rsList;
	}
	
	//대상자총괄관리 체성분 목록 조회
	public List<Map<String, Object>> selectBodyCompChartList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormeasrinfo.selectBodyCompChartList", param);
		return rsList;
	}

	//대상자총괄관리 혈압 목록 조회
	public List<Map<String, Object>> selectBloodPressChartList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormeasrinfo.selectBloodPressChartList", param);
		return rsList;
	}

	//대상자총괄관리 혈당 목록 조회
	public List<Map<String, Object>> selectBloodSugarChartList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormeasrinfo.selectBloodSugarChartList", param);
		return rsList;
	}	
	
}
