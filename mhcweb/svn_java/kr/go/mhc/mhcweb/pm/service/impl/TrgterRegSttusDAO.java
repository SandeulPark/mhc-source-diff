package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : TrgterRegSttusDAO.java
 * @Description : 관리자 WEB에서 사용하는 대상자 등록현황 실적관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.pm.TrgterRegSttusDAO")
public class TrgterRegSttusDAO extends EgovAbstractMapper {

	//대상자 등록 일반 현황 조회
	public List<Map<String, Object>> selectTrgterRegSttusGenList(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusGenList", param);
		return rsList;
	}
	
	//대상자 등록 위험요인 현황 조회
	public List<Map<String, Object>> selectTrgterRegSttusDenList(Map<String, Object> param) throws Exception {
		System.out.println("PARAM ::::::::::::::::" + param);
		
		
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusDenList", param);
		return rsList;
	}

	//대상자 등록 군분류 현황 조회
	public List<Map<String, Object>> selectTrgterRegSttusDivList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusDivList", param);
		return rsList;
	}
	
	
	//대상자 등록 일반 현황 대상자 목록 조회
	public List<Map<String, Object>> selectTrgterRegSttusGenTrgterList(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusGenTrgterList", param);
		return rsList;
	}
	
	//대상자 등록 일반 현황 조회(실적 개편)
	public List<Map<String, Object>> selectTrgterRegSttusGenListNew(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusGenListNew", param);
		return rsList;
	}
	
	//대상자 등록 위험요인 현황 조회(실적 개편)
	public List<Map<String, Object>> selectTrgterRegSttusDenListNew(Map<String, Object> param) throws Exception {
		System.out.println("PARAM ::::::::::::::::" + param);
		
		
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusDenListNew", param);
		return rsList;
	}

	//대상자 등록 군분류 현황 조회(실적 개편)
	public List<Map<String, Object>> selectTrgterRegSttusDivListNew(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusDivListNew", param);
		return rsList;
	}
	
	
	//대상자 등록 일반 현황 대상자 목록 조회(실적 개편)
	public List<Map<String, Object>> selectTrgterRegSttusGenTrgterListNew(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterregsttus.selectTrgterRegSttusGenTrgterListNew", param);
		return rsList;
	}

}
