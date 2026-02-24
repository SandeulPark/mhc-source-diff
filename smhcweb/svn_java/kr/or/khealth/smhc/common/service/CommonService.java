package kr.or.khealth.smhc.common.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : CommonService.java
 * @Description : 모바일 헬스케어에서 사용하는 통합공통업무를 관리하는 서비스 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface CommonService {

	/**
	 * 공통코드 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmmnCd(Map<String, Object> param) throws Exception;
	
	/**
	 * 메뉴 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmmnMenu(Map<String, Object> param) throws Exception;
	
	/**
	 * 메뉴 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public Map<String, String> selectCmmnMenuInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부_파일_순번 조회
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public String selectAttchFileSnSeq() throws Exception;
	
	/**
	 * 첨부_파일_상세_순번 조회
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public String selectAttchFileDtlsSn(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부_파일 정보 저장
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public int insertAttchFile(List<Map<String,String>> fileList) throws Exception;
	
	/**
	 * 첨부_파일 정보 삭제
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public int deleteAttchFileInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부_파일 정보 삭제
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public int updateAttchFileUseYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부_파일 정보 조회
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectAttchFile(Map<String, Object> param) throws Exception;
	
	/**
	 * 관리자 로그 정보
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void insertCmmnLogInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 동영상 첨부파일 정보 저장
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void insertVideoAttchFile(Map<String, Object> param) throws Exception;
	
	/**
	 * 동영상 첨부파일 정보 삭제
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void deleteVideoAttchFile(Map<String, Object> param) throws Exception;
	
	/**
	 * 실적 주차 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectTodayWeekNm(Map<String, Object> param) throws Exception;	
	
	
	/**
	 * 대상자 개인정보 조회 로그 정보
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void insertCmmnLogPerSchInfo(Map<String, Object> param) throws Exception;
	
	
	/**
	 * ID 중복체크
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public Map<String, Object> idChk(Map<String, Object> param) throws Exception;	

	public Map<String,Object> selectCmmnCdUseYn(Map<String, Object> param) throws Exception;
}
