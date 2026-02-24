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
	 * 기기 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> checkAppInfo(Map<String, Object> param) throws Exception;

	/**
	 * 관리자 웹 토큰 정보 업데이트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateTokenInfo(Map<String, Object> param) throws Exception;
		
	/**
	 * 사용자 인증서 정보 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void setCertDn(Map<String, Object> param) throws Exception;

	
	
	/**
	 * 관리자웹_사용자 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public Map<String, Object> checkWebLoginUser(Map<String, Object> param) throws Exception;
	
	/**
	 * 사용자 정보 확인
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> checkLoginUserSttus(Map<String,Object> param) throws Exception;
	
	/**
	 * 사용자 인증서 비밀번호 체크 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateDnPwCheck(Map<String, Object> param) throws Exception;

	/**
	 * 사용자 인증서 로그인 성공 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void updateDnLoginSucss(Map<String, Object> param) throws Exception;
	
	/**
	 * 타블렛_사용자 정보 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public Map<String, Object> checkTabletLoginUser(Map<String, Object> param) throws Exception;	
}
