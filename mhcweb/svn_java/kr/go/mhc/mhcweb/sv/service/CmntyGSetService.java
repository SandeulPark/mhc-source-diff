package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : CmntyGSetService.java
 * @Description : 관리자 WEB에서 사용하는 그룹설정 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.18		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface CmntyGSetService {
	
	/**
	 * 그룹 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getCmntyGList(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 대상자 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getCmntyGTrgterList(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 그룹 코드 조회
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 단일 ROW
	 * @throws Exception 
	 */
	public Map<String,Object> getNewCmntyGSn(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 등록
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getCmntyGInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 수정
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getCmntyGUpd(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 삭제(사용안함)
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getCmntyGUseN(Map<String, Object> param) throws Exception;
	

	/**
	 * 그룹 대상자 삭제(사용안함)
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getCmntyGTrgterUseN(Map<String, Object> param) throws Exception;
	
	/**
	 * 관리군 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getGclasList() throws Exception;
	
	/**
	 * 추가 대상자 조회 (팝업)
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getAddTrgterList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 그룹 대상자 등록
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getCmntyGTrgterInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 삭제
	 * @param param
	 */
	
	public void delCmntyGroup(Map<String, Object> param);
	
	/**
	 * 그룹 대상자 삭제
	 * @param param
	 */
	public void delCmntyGroupMember(Map<String, Object> param);
}
