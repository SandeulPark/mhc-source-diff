package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : MajorResultIndexDAO.java
 * @Description : 관리자 WEB에서 사용하는 주요 성과 지표 실적관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.11.21		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.11.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.pm.MajorResultIndexDAO")
public class MajorResultIndexDAO extends EgovAbstractMapper {
	
	// 개요 조회
	public List<Map<String, Object>> selectSummaryList(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectSummaryList", param);
		return rsList;
	}
	
	//개요 대상자 목록 조회
	public List<Map<String, Object>> selectSummaryTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectSummaryTrgterList", param);
		return rsList;
	}	
	

	// 지속참여자 및 중도탈락자 조회
	public List<Map<String, Object>> selectTrgterPartDropList(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectTrgterPartDropList", param);
		return rsList;
	}
	
	//지속참여자 및 중도탈락자 대상자 목록 조회
	public List<Map<String, Object>> selectTrgterPartDropTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectTrgterPartDropTrgterList", param);
		return rsList;
	}
	
	//건강행태 1개이상 개선자 조회

	public List<Map<String, Object>> selectHealthResultImpList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthResultImpList", param);
		return rsList;
	}
	
	
	//건강행태 1개이상 개선자 대상자 목록 조회
	public List<Map<String, Object>> selectHealthResultImpTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthResultImpTrgterList", param);
		return rsList;
	}
	
	//건강위험요인 1개이상 감소자 조회
	public List<Map<String, Object>> selectHealthDangerDecList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthDangerDecList", param);
		return rsList;
	}

	//건강위험요인 1개이상 감소자 대상자 목록 조회		
	public List<Map<String, Object>> selectHealthDangerDecTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthDangerDecTrgterList", param);
		return rsList;
	}
	
	//만족도 목록 조회
	public List<Map<String, Object>> selectServeySatisScoreList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectServeySatisScoreList", param);
		return rsList;
	}	
	
	//만족도 대상자 화면 목록 조회	
	public List<Map<String, Object>> selectServeySatisScoreTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectServeySatisScoreTrgterList", param);
		return rsList;
	}

	public List<Map<String, Object>> selectSummaryTrgerList(Map<String, Object> param) throws Exception {	
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectSummaryTrgerList", param);
		return rsList;
	}

	// 개요(실적개편)
	public List<Map<String, Object>> selectSummaryListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectSummaryListNew", param);
		return rsList;
	}
	// 지속참여 및 중도탈락자 조회(실적개편)
	public List<Map<String, Object>> selectTrgterPartDropListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectTrgterPartDropListNew", param);
		return rsList;
	}
	//건강행태 1개이상 개선자 조회(실적개편)
	public List<Map<String, Object>> selectHealthResultImpListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthResultImpListNew", param);
		return rsList;
	}
	// 건강위험요인 1개 감소자 조회(실적개편)
	public List<Map<String, Object>> selectHealthDangerDecListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthDangerDecListNew", param);
		return rsList;
	}
	
	// 만족도 조회(실적개편)
	public List<Map<String, Object>> selectServeySatisScoreListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectServeySatisScoreListNew", param);
		return rsList;
	}
	// 개요 > 상세조회(실적개편)
	public List<Map<String, Object>> selectSummaryTrgerListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectSummaryTrgerListNew", param);
		return rsList;
	}
	// 지속참여 및 중도탈락 > 상세조회(실적개편)
	public List<Map<String, Object>> selectTrgterPartDropTrgterListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectTrgterPartDropTrgterListNew", param);
		return rsList;
	}
	// 건강행태 1개이상 개선자 > 상세조회(실적개편)
	public List<Map<String, Object>> selectHealthResultImpTrgterListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthResultImpTrgterListNew", param);
		return rsList;
	}
	// 건강위험요인 1개 감소자 > 상세조회(실적개편)
	public List<Map<String, Object>> selectHealthDangerDecTrgterListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectHealthDangerDecTrgterListNew", param);
		return rsList;
	}
	// 만족도점수 > 상세조회(실적개편)
	public List<Map<String, Object>> selectServeySatisScoreTrgterListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.majorresultindex.selectServeySatisScoreTrgterListNew", param);
		return rsList;
	}	
	

	
}
