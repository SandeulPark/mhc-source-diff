package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : BoardDAO.java
 * @Description : 관리자 WEB에서 사용하는 게시판 관리하는 DAO Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sm.ServeyReSearchMngtDAO")
public class ServeyReSearchMngtDAO extends DMultiEgovAbstractMapper {

	/**
	 * 설문조사 화면 설문개요 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyResearchList", param);	
		return rsList;  
	}
	
	/**
	 * 설문조사 화면 설문개요 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyEndList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyEndList", param);	
		return rsList;  
	}
	
	/**
	 * 설문조사 화면 설문개요 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAppMeasureList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyAppMeasureList", param);	
		return rsList;  
	}
		
	
	/**
	 * 설문개요 상세 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> serveyResearchSumryDtls(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.sm.serveyresearchmngt.serveyResearchSumryDtls", param);
		return rsMap;
	}
	
	/**
	 * 설문개요 상세 기관 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchSumryList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		if(param.get("SUBMIT_CLF")!=null){
			if(param.get("SUBMIT_CLF").equals("ALL")) {
				rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyResearchAllSumryList", param);
			} else {
				rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyResearchSumryList", param);
			}
		} else {//보건소 관리자
			rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyResearchMngSumryList", param);
		}
		return rsList;
	}
	
	/**
	 * 설문개요  기관 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyRegOrgList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyRegOrgList", param);
		return rsList;
	}
	
	/**
	 * 설문항목 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchItemList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyResearchItemList", param);
		return rsList;
	}
	
	/**
	 * 설문개요 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String serveyResearchInsert(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.sm.serveyresearchmngt.serveyResearchInsert", param);
		String serveySn = (String) param.get("newServeySn");
		return serveySn;
	}
	
	/**
	 * 설문항목 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchItemDel(Map<String, Object> param) throws Exception{
		int rsInt = delete("mhc.web.sm.serveyresearchmngt.serveyResearchItemDel", param);
		return rsInt;
	}
	
	/**
	 * 설문대상 기관 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteRegOrgList(Map<String, Object> param) throws Exception{
		delete("mhc.web.sm.serveyresearchmngt.deleteRegOrgList", param);		
	}
		
	
	/**
	 * 설문항목 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchItemAdd(Map<String, Object> param) throws Exception{
		int rsInt = insert("mhc.web.sm.serveyresearchmngt.serveyResearchItemAdd", param);
		return rsInt;
	}
	
	/**
	 * 설문답변 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> serveyResearchAnswrMastr(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.sm.serveyresearchmngt.serveyResearchAnswrMastr", param);
		return rsMap;
	}
	
	/**
	 * 설문답변 통계상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAnswrStatsList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyAnswrStatsList", param);
		return rsList;
	}
	
	/**
	 * 설문답변(app) 통계상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAppDtlsMeasureList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyAppDtlsMeasureList", param);
		return rsList;
	}
	
	/**
	 * 설문답변 상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchAnswrList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyResearchAnswrList", param);
		return rsList;
	}
	
	/**
	 * 설문답변 마스터 정보 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchAnswrMastrInsert(Map<String, Object> param) throws Exception{
		int rsInt = insert("mhc.web.sm.serveyresearchmngt.serveyResearchAnswrMastrInsert", param);
		return rsInt;
	}
	
	/**
	 * 설문항목 답변 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchAnswrDel(Map<String, Object> param) throws Exception{
		int rsInt = delete("mhc.web.sm.serveyresearchmngt.serveyResearchAnswrDel", param);
		return rsInt;
	}
	
	/**
	 * 설문항목 답변 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchAnswrInsert(Map<String, Object> param) throws Exception{
		int rsInt = insert("mhc.web.sm.serveyresearchmngt.serveyResearchAnswrInsert", param);
		return rsInt;
	}
	
	/**
	 * 보건소 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> regOrgList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.regOrgList", param);
		return rsList;
	}


	
	/**
	 * 설문조사 기관지정 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void selectServeyOrgInsert(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.sm.serveyresearchmngt.selectServeyOrgInsert", param);		
	}
	/*
	 * public void insertOrg(Map<String, Object> param) { int rsInt =
	 * update("mhc.web.sm.serveyresearchmngt.serveyResearchInsert", param);
	 *
	 * }
	 */

	/**
	 * 대상자용 설문조사 화면 설문개요 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyTrgtResearchList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyTrgtResearchList", param);
		return rsList;
	}

	/**
	 * 설문 주관식답변 상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAnswrInpStatsList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyAnswrInpStatsList", param);
		return rsList;
	}

	/**
	 * 대상자용 설문개요 상세 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyTrgtResearchSumryDtls(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.serveyTrgtResearchSumryDtls", param);
		return rsList;
	}

	/**
	 * 대상자용 설문조사 설문완료 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> trgtServeyEndList(Map<String, Object> param) {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.serveyresearchmngt.trgtServeyEndList", param);
		return rsList;
	}

	public String serveyResearchAnswrClf(Map<String, Object> param) {
		return selectOne("mhc.web.sm.serveyresearchmngt.serveyResearchAnswrClf", param);
	}

	public int regOrgListCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("mhc.web.sm.serveyresearchmngt.regOrgListCnt", param);
	}

	public Map<String, Object> getServeyUserCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("mhc.web.sm.serveyresearchmngt.getServeyUserCnt", param);
	}

	public Map<String, Object> getTrgtServeyUserCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("mhc.web.sm.serveyresearchmngt.getTrgtServeyUserCnt", param);
	}
}
