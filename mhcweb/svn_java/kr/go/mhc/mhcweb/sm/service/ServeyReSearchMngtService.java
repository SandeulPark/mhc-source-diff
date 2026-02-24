package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : BoardService.java
 * @Description : 관리자 WEB에서 사용하는 질의응답 관리하는 서비스 interface
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

public interface ServeyReSearchMngtService {

	/**
	 * 설문조사 화면 설문개요 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문조사 화면 완료목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyEndList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문조사 화면 완료목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAppMeasureList(Map<String, Object> param) throws Exception;
			
	/**
	 * 설문개요 상세 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> serveyResearchSumryDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문개요 상세 기관 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchSumryList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문개요 기관상세
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyRegOrgList(Map<String, Object> param) throws Exception;
			
	/**
	 * 설문항목 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchItemList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문개요 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String serveyResearchInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문조사 기관지정 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void selectServeyOrgInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문대상 기관 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteRegOrgList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문항목 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchItemDel(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문조사 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String serveyResearchRegit(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문답변 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> serveyResearchAnswrMastr(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문답변 상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyResearchAnswrList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문답변 통계 상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAnswrStatsList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문항목 답변 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int serveyResearchAnswrInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 보건소 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> regOrgList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문답변 (APP) 통계 상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAppDtlsMeasureList(Map<String, Object> param) throws Exception;


	/**
	 * 대상자용 설문조사 화면 설문개요 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyTrgtResearchList(Map<String, Object> param) throws Exception;

	/**
	 * 대상자용 설문개요 상세 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> serveyTrgtReSearchMngtDtls(Map<String, Object> param) throws Exception;

	/**
	 * 설문 주관식답변 상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyAnswrInpStatsList(Map<String, Object> param) throws Exception;

	/**
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> serveyTrgtResearchSumryDtls(Map<String, Object> param) throws Exception;

	/**
	 * 대상자용 설문조사 설문완료 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> trgtServeyEndList(Map<String, Object> param) throws Exception;

	/**
	 * 대상자용 설문조사 전체/대표 관리자 값 확인
	 * @param param
	 * @return
	 */
	public String serveyResearchAnswrClf(Map<String, Object> param) throws Exception;
	
	
	public int regOrgListCnt(Map<String, Object> param) throws Exception;

	public Map<String, Object> getServeyUserCnt(Map<String, Object> param) throws Exception;

	public Map<String, Object> getTrgtServeyUserCnt(Map<String, Object> param) throws Exception;
}
