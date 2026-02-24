package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : OrgMngtService.java
 * @Description : 관리자 WEB에서 사용하는 기관정보 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.04.12		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.04.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface OrgMngtService {
	
	/**
	 * 기관관리 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getOrgMngtList(Map<String, Object> param) throws Exception;

	/**
	 * 기관관리 상세 정보 조회(자가관리군 집중상담/중간검진 진행 여부)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getOrgDtlsList(Map<String, Object> param) throws Exception;
	/**
	 * 기관 등록 및 수정
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int saveOrgMngt(Map<String, Object> param) throws Exception;
	//20191203양현우 추가
	/**
	 * 순서 변경 팝업
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectOrgMngtPop(Map<String, Object> param) throws Exception;
	//20191203양현우 추가 끝
	
	/**
	 * 관리자 등록 승인
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int updateOrgApprovalYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 기관 ,관리자 등록 및 수정
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int saveOrgMngter(Map<String, Object> param) throws Exception;

	/**
	 * 보건소 기관 콤보박스
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectOrgList(Map<String, Object> param) throws Exception;
}
