package kr.or.khealth.smhc.common.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : CommonUserService.java
 * @Description : 모바일 헬스케어에서 사용하는 자용자공통모듈 service Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.05		김보람			최초생성
 *
 * @author gst
 * @since 2016.08.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface CommonUserService {

	/**
	 * 사용자 목록 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectUserList(Map<String, Object> param) throws Exception;
	
	/**
	 * 사용자 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public Map<String, String> selectUser(Map<String, Object> param) throws Exception;
	
	/**
	 * 사용자 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public Map<String, Object> checkLoginUser(Map<String, Object> param) throws Exception;
	
	/**
	 * 관리자웹_사용자 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public Map<String, String> checkWebLoginUser(Map<String, Object> param) throws Exception;
	
	/**
	 * 기기 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> checkAppInfo(Map<String, Object> param) throws Exception;
	 
	 /**
	 * 전자 서명 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> digiSignInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 전자 서명 유무 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDigiSignChk(Map<String, Object> param) throws Exception;
	
	/**
	 * 전자 서명 정보 조회 저장
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public int digiSignInsert(Map<String,Object>param) throws Exception;
	
	/**
	 * 전자 서명 정보 조회 수정
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public int digiSignUpdate(Map<String,Object>param) throws Exception;
}
