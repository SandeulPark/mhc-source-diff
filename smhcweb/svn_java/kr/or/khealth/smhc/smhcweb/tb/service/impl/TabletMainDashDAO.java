package kr.or.khealth.smhc.smhcweb.tb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : SeniorMeasrInfoDAO.java
 * @Description : 관리자 TABLET에서 사용하는 어르신 건강  업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.19		양현우			수정
 *
 * @author thejoin
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tb.TabletMainDashDAO")
public class TabletMainDashDAO extends EgovAbstractMapper{
	
	//태블릿 메인 화면 전체 대상자 수 조회
	public Map<String, Object> selectTrgterTodayTotalCount(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.tb.maindash.selectTrgterTodayTotalCount", param);
		return rsMap;
	}
	
	//태블릿 메인 화면 예정 대상자 수 조회
	public Map<String, Object> selectTrgterTodayIngCount(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.tb.maindash.selectTrgterTodayIngCount", param);
		return rsMap;
	}
	
	//태블릿 메인 화면 완료 대상자 수 조회
	public Map<String, Object> selectTrgterTodayEndCount(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.tb.maindash.selectTrgterTodayEndCount", param);
		return rsMap;
	}
	
	//태블릿 메인 화면 대상자 리스트 조회
	public List<Map<String, Object>> selectTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tb.maindash.selectTrgterList", param);
		return rsList;
	}

	//태블릿 메인 화면 대상자 리스트 조회
	public List<Map<String, Object>> selectAllTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tb.maindash.selectAllTrgterList", param);
		return rsList;
	}	
	
}
