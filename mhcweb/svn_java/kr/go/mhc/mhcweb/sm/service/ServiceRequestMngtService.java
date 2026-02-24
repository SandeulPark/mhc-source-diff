package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : ServiceRequestMngtService.java
 * @Description : 관리자 WEB에서 사용하는 건강이상수치 설정 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.30		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface ServiceRequestMngtService {
	
	/**
	 * 운영처리 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getServiceRequestList(Map<String, Object> param) throws Exception;
		
	/**
	 * 운영처리 목록 건수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int getServiceRequestListCount(Map<String, Object> param) throws Exception;
		
	/**
	 * 운영처리 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public void saveServiceRequestMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 운영처리 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public Map<String, Object> getServiceRequestDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상메뉴 콤보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getTrgtMenuCombo(Map<String, Object> param) throws Exception;
	
	/**
	 * 운영처리 엑셀 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getServiceRequestExcelList(Map<String, Object> param) throws Exception;
	
	/**
	 * 운영처리 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public void delServiceRequest(Map<String, Object> param) throws Exception;
	/**
	 * 기관검색 팝업 기관조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getOrgChkList(Map<String, Object> param)throws Exception;
	/**
	 * 관리자명 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<String> selectMngtList()throws Exception;
}
